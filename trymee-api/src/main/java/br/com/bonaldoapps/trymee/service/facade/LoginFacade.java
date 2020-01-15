/**
 * 
 */
package br.com.bonaldoapps.trymee.service.facade;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.bonaldoapps.trymee.dao.SubscriptionDAO;
import br.com.bonaldoapps.trymee.dao.UserDAO;
import br.com.bonaldoapps.trymee.entity.Subscription;
import br.com.bonaldoapps.trymee.entity.User;
import br.com.bonaldoapps.trymee.service.UserPassHashGenerator;

/**
 * @author Daniel
 *
 */
@Stateless
public class LoginFacade {

	@Inject
	private HttpServletRequest req;

	@Inject
	private UserDAO userDAO;

	@Inject
	private SubscriptionDAO subscriptionDAO;

	@Inject
	private UserTokenFacade userToken;

	@Inject
	private UserPassHashGenerator passHashGenerator;

	public String doLogin(String email, String pass) throws Exception {

		String ip = req.getLocalAddr();

		String hashPass = passHashGenerator.createHashFromPass(pass);

		User user = userDAO.listwithParams(email, hashPass);

		if (user == null)
			throw new Exception("Usuário/senha inválidos. Confira os dados e tente novamente.");

		List<Subscription> subs = subscriptionDAO.listSubscriptions(user.getId(), new Date(), true);

		if (subs == null || subs.size() != 1)
			throw new Exception("A sua assinatura do serviço já expirou");

		String token = userToken.createUserToken(user, ip);

		return token;

	}

}
