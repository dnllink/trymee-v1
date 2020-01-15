package br.com.bonaldoapps.trymee.service.facade;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.bonaldoapps.trymee.email.SparkPostIntegrationFacade;
import br.com.bonaldoapps.trymee.entity.Link;

@Stateless
public class SendEmailFacade {

	@Inject
	private SparkPostIntegrationFacade spif;

	public void sendEmail(Link link) throws Exception {

		spif.sendEmailBySP(link);

	}

}