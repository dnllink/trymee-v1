package br.com.bonaldoapps.trymee.service.facade;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.bonaldoapps.trymee.dao.CandidateDAO;
import br.com.bonaldoapps.trymee.dao.LinkDAO;
import br.com.bonaldoapps.trymee.dao.ProcessDAO;
import br.com.bonaldoapps.trymee.dao.SubscriptionDAO;
import br.com.bonaldoapps.trymee.email.SendEmailWithAmazonSES;
import br.com.bonaldoapps.trymee.email.impl.TestLinkEmail;
import br.com.bonaldoapps.trymee.entity.Candidate;
import br.com.bonaldoapps.trymee.entity.Link;
import br.com.bonaldoapps.trymee.entity.Plan;
import br.com.bonaldoapps.trymee.entity.Process;
import br.com.bonaldoapps.trymee.entity.Subscription;

@Stateless
public class SendLinkFacade {

	@Inject
	private ProcessDAO processDAO;

	@Inject
	private CandidateDAO candidateDAO;

	@Inject
	private LinkDAO linkDAO;

	@Inject
	private LinkTokenFacade linkToken;

	@Inject
	private SendEmailWithAmazonSES spAPI;

	@Inject
	private SubscriptionDAO subscriptionDAO;

	private static final String URL_TEST = "trymee-test-web/#/answer-test/";

	public String sendLink(Long idProcess, Long idCandidate, Boolean resend, Long idUser, String host)
			throws Exception {

		Candidate candidate;

		Process process = processDAO.find(idProcess, idUser, false, true);

		if (process == null)
			return "Processo informado não foi encontrado.";

		if (!process.hasTest())
			return "Processo ainda não possui uma prova. Associe uma prova e tente novamente.";

		if (!process.hasCandidates())
			return "Processo ainda não possui candidatos. Associe alguns candidatos e tente novamente.";

		List<Link> links = linkDAO.listWithParams(process.getId(), idCandidate, process.getUser().getId());

		Subscription sub = null;

		int difference = ((idCandidate == null ? process.getCandidates().size() : 1) - links.size());

		List<Subscription> subs = subscriptionDAO.listSubscriptions(process.getUser().getId(), new Date(), true);

		if (subs != null && subs.size() == 1) {

			sub = subs.get(0);

		}

		if (sub == null)
			return "Assinatura inválida, entre em contato com o suporte em suporte@trymee.com.br";

		if (Plan.QUANTITY_PLAN.equals(sub.getPlan().getType())) {

			if (difference > sub.getTestsLeft()) {

				return "Você não possui uma quantidade de testes a serem enviados suficiente. Envie os links individualmente ou adquira um novo plano.";

			}

		} else {

			sub = null;

		}

		if (idCandidate != null) {

			candidate = candidateDAO.find(idCandidate, idUser, false, false, true);

			if (candidate == null)
				return "Candidato informado não foi encontrado.";

			validateAndSend(process, candidate, resend, sub, host);

		} else {

			for (Candidate c : process.getCandidates()) {

				validateAndSend(process, c, resend, sub, host);

			}

		}

		return new String();

	}

	private void validateAndSend(Process process, Candidate candidate, Boolean resend, Subscription sub, String host)
			throws Exception {

		List<Link> links = linkDAO.listWithParams(process.getId(), candidate.getId(), process.getUser().getId());

		Boolean validSubscription = true;

		if (!validSubscription)
			throw new Exception("Assinatura expirada. Renove já e continue enviando provas!");

		if (links != null && links.size() > 0) {

			if (resend) {
				for (Link l : links) {

					TestLinkEmail mail = new TestLinkEmail(l.getCandidate().getName(), l.getCandidate().getEmail(),
							l.getUser().getEmail(), l.getUrl());

					spAPI.sendEmail(mail);

				}
			}

		} else {

			String testToken = linkToken.createLinkToken(process.getUser().getId(), process.getId(), candidate.getId());

			Link link = new Link();
			link.setProcess(process);
			link.setCandidate(candidate);
			link.setUrl(host + URL_TEST + testToken);
			link.setCode(testToken);
			link.setDtFirstSent(new Date());
			link.setUser(process.getUser());

			linkDAO.save(link);

			if (sub != null) {

				sub.setTestsLeft(sub.getTestsLeft() - 1);
				subscriptionDAO.update(sub);

			}

			TestLinkEmail mail = new TestLinkEmail(link.getCandidate().getName(), link.getCandidate().getEmail(),
					link.getUser().getEmail(), link.getUrl());

			spAPI.sendEmail(mail);
		}

	}

}
