/**
 * 
 */
package br.com.bonaldoapps.trymee.email;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.com.bonaldoapps.trymee.dao.ParameterDAO;
import br.com.bonaldoapps.trymee.email.interf.TMEmail;
import br.com.bonaldoapps.trymee.entity.Parameter;
import br.com.bonaldoapps.trymee.service.facade.PropertiesFacade;

/**
 * @author Daniel
 *
 */
@Stateless
public class SendEmailWithAmazonSES {

	private static final Logger LOGGER = LogManager.getLogManager().getLogger("SendEmailWithSPApi");

	@Inject
	private PropertiesFacade emailProperties;

	@Inject
	private ParameterDAO parameterDAO;

	public static final String SMTP_USER = "tm.email.amazonses.smtp.user";
	public static final String SMTP_PASS = "tm.email.amazonses.smtp.pass";
	public static final String SMTP_HOST = "tm.email.amazonses.smtp.host";
	public static final String SMTP_PORT = "tm.email.amazonses.smtp.port";
	public static final String EMAIL_ACTIVE = "tm.email.delivery.active";

	public void sendEmail(TMEmail email) throws Exception {

		Properties p;
		try {
			p = emailProperties.readProperties();
		} catch (IOException e) {
			LOGGER.info(e.getMessage());
			throw new Exception(
					"Não foi possível enviar o email, entre em contato com o atendimento em suporte@trymee.com.br");
		}

		String deliveryEmailStatus = parameterDAO.findParameter(p.getProperty(EMAIL_ACTIVE));

		if (Parameter.INACTIVE.equals(deliveryEmailStatus)) {
			return;
		}

		ExecutorService executorService = Executors.newSingleThreadExecutor();

		executorService.execute(new Runnable() {
			public void run() {
				try {
					sendAsyncEmail(email, p);
				} catch (UnsupportedEncodingException e) {
					LOGGER.log(Level.SEVERE, e.getMessage());
				} catch (MessagingException e) {
					LOGGER.log(Level.SEVERE, e.getMessage());
				}
			}
		});

		executorService.shutdown();

	}

	private void sendAsyncEmail(TMEmail email, Properties p) throws UnsupportedEncodingException, MessagingException {

		String to = email.recipients().get(0).getAddress();
		String from = email.from();
		String fromName = email.fromName();
		String subject = email.subject();

		String body = parameterDAO.findParameter(p.getProperty(email.template()));

		for (Entry<String, String> entry : email.data().entrySet()) {

			body = body.replace("{{" + entry.getKey() + "}}", entry.getValue());

		}

		Properties props = System.getProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.port", p.getProperty(SMTP_PORT));
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props);

		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(from, fromName));
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
		msg.setSubject(subject);
		msg.setContent(body, "text/html");

		// msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);

		Transport transport = session.getTransport();

		try {

			transport.connect(p.getProperty(SMTP_HOST), p.getProperty(SMTP_USER), p.getProperty(SMTP_PASS));
			transport.sendMessage(msg, msg.getAllRecipients());
			LOGGER.info("Email enviado com sucesso para " + to);

		} catch (Exception ex) {

			LOGGER.info("Erro ao enviar email para" + to);
			LOGGER.info(ex.getMessage());

		} finally {
			transport.close();
		}

	}

}
