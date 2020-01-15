/**
 * 
 */
package br.com.bonaldoapps.trymee.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.bonaldoapps.trymee.dao.PlanDAO;
import br.com.bonaldoapps.trymee.dao.SubscriptionDAO;
import br.com.bonaldoapps.trymee.dao.UserDAO;
import br.com.bonaldoapps.trymee.email.SendEmailWithAmazonSES;
import br.com.bonaldoapps.trymee.email.impl.WelcomeEmail;
import br.com.bonaldoapps.trymee.entity.Plan;
import br.com.bonaldoapps.trymee.entity.Process;
import br.com.bonaldoapps.trymee.entity.Subscription;
import br.com.bonaldoapps.trymee.entity.User;
import br.com.bonaldoapps.trymee.entity.dto.SubscriptionDTO;

/**
 * @author Daniel
 *
 */
@Stateless
public class SubscriptionService {

	@Inject
	private SubscriptionDAO subscriptionDAO;

	@Inject
	private UserDAO userDAO;

	@Inject
	private PlanDAO planDAO;

	@Inject
	private UserPassHashGenerator passHashGenerator;

	@Inject
	private SendEmailWithAmazonSES sendEmail;
	
	@Inject	
	private TutorialService tutorial;

	/**
	 * @param idUser
	 * @return
	 */
	public SubscriptionDTO findActiveSubscription(Long idUser) {

		List<Subscription> subs = subscriptionDAO.listSubscriptions(idUser, new Date(), true);
		SubscriptionDTO dto = null;

		if (subs != null && subs.size() == 1) {

			dto = new SubscriptionDTO(subs.get(0));

		}

		return dto;
	}

	public void updateUserPassword(Long idUser, String newPass) throws Exception {

		User user = userDAO.find(idUser);

		if (user == null)
			throw new Exception(
					"Ocorreu um erro, o usuário não foi encontrado, entre em contato com o suporte em suporte@trymee.com.br");

		String hash = passHashGenerator.createHashFromPass(newPass);

		user.setPassword(hash);

		userDAO.update(user);

	}

	public void createSubscription(User user, Plan plan) {

	}

	public Map<String, Long> createSubscription(String name, String email, String pass, String tel, String plan) throws Exception {
		
		Map<String, Long> userData = new HashMap<String, Long>();

		if (emailAlreadyExists(email))
			throw new Exception("O email informado já possui um cadastro");

		User user = new User(name, email);

		user.setTelephone(tel == null ? null : tel.length() > 0 ? tel : null);

		user.setPassword(passHashGenerator.createHashFromPass(pass));

		Plan freePlan = planDAO.findFreePlan();

		Subscription sub = new Subscription(user, freePlan);

		try {

			subscriptionDAO.save(sub);

			WelcomeEmail mail = new WelcomeEmail(user.getName(), user.getEmail(), sub.getPlan().getName(),
					sub.getDateEndPeriod(), sub.getPlan().getTestQuantity(), sub.getPlan().getValue());

			sendEmail.sendEmail(mail);
			
			Process p = tutorial.createTutorial(user);
			
			userData.put("idUser", user.getId());
			userData.put("idProcess", p.getId());

		} catch (Exception e) {
			throw new Exception("Ocorreu um erro ao realizar o cadastro, entre em contato com suporte@trymee.com.br");
		}
		
		return userData;

	}

	private Boolean emailAlreadyExists(String email) {

		User user = userDAO.listwithParams(email, null);

		return user != null;

	}

}
