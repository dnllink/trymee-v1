package br.com.bonaldoapps.trymee.service.facade;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.bonaldoapps.trymee.dao.TokenDAO;
import br.com.bonaldoapps.trymee.entity.Token;
import br.com.bonaldoapps.trymee.entity.User;

@Stateless
public class UserTokenFacade {

	// private static final Logger LOGGER = Logger.getLogger("UserTokenFacade");

	@Inject
	private EncriptService cript;

	@Inject
	private TokenDAO tokenDAO;

	public String createUserToken(User user, String ip) {

		// TODO falta invalidar os tokens existentes do usuario

		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy hhmmss");

		Calendar cal = Calendar.getInstance();
		String datetime = sdf.format(cal.getTime());

		String tokenStr = "[ " + ip + "." + user.getId() + "." + datetime + "]";
		String criptToken = cript.encriptText(tokenStr);
		String base64Token = Base64.getEncoder().encodeToString(criptToken.getBytes());

		// base64Token = new String(Base64.getDecoder().decode(base64Token));
		// exemplo de decode do token

		Token token = new Token();

		token.setActive(true);
		token.setDateCreation(cal.getTime());
		token.setIp(ip);
		token.setToken(base64Token);
		token.setUser(user);

		tokenDAO.save(token);

		return base64Token;

	}

}
