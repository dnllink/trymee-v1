/**
 * 
 */
package br.com.bonaldoapps.trymee.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.bonaldoapps.trymee.dao.AlternativeDAO;
import br.com.bonaldoapps.trymee.dao.AnswerDAO;
import br.com.bonaldoapps.trymee.dao.LinkDAO;
import br.com.bonaldoapps.trymee.dao.QuestionDAO;
import br.com.bonaldoapps.trymee.entity.Answer;
import br.com.bonaldoapps.trymee.entity.Link;
import br.com.bonaldoapps.trymee.entity.dto.QuestionToAnswerDTO;
import br.com.bonaldoapps.trymee.entity.dto.TestToAnswerDTO;
import br.com.bonaldoapps.trymee.entity.enums.QuestionTypeEnum;
import br.com.bonaldoapps.trymee.exception.InvalidLinkException;

/**
 * @author Daniel
 *
 */
@Stateless
public class AnswerTestService {

	@Inject
	private LinkDAO linkDAO;

	@Inject
	private QuestionDAO questionDAO;

	@Inject
	private AnswerDAO answerDAO;

	@Inject
	private AlternativeDAO alternativeDAO;

	/**
	 * @param test
	 * @throws InvalidLinkException
	 */
	public void saveAnswers(TestToAnswerDTO test) throws InvalidLinkException {

		Link link = linkDAO.findByToken(test.getT());

		if (!link.getActive()) {
			throw new InvalidLinkException("Este link já foi utilizado e não é mais válido.");
		}

		Answer answer;

		for (QuestionToAnswerDTO q : test.getQs()) {

			answer = new Answer();

			answer.setUser(link.getUser());
			answer.setQuestion(questionDAO.find(q.getI(), link.getUser().getId(), false));
			answer.setProcess(link.getProcess());
			answer.setCandidate(link.getCandidate());

			if (QuestionTypeEnum.DISCURSIVA.getId() == q.getT()) {
				answer.setAnswerText(q.getA());
			} else {
				answer.setAnswerAlternative(alternativeDAO.find(q.getC()));
			}

			answerDAO.save(answer);

		}

		link.setActive(false);

		linkDAO.update(link);

	}

}
