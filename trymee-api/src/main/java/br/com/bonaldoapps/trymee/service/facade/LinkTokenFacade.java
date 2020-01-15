package br.com.bonaldoapps.trymee.service.facade;

import java.util.Base64;
import java.util.Calendar;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class LinkTokenFacade {

	@Inject
	private EncriptService cript;

	public String createLinkToken(Long idUser, Long idProcess, Long idCandidate) {

		Calendar cal = Calendar.getInstance();

		String hour = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":"
				+ cal.get(Calendar.MILLISECOND);

		String token = "[ " + idCandidate + "." + idProcess + "." + idUser + "." + hour + "]";

		String criptToken = cript.encriptText(token);

		String base64Token = Base64.getEncoder().encodeToString(criptToken.getBytes());

		return base64Token;

	}

}
