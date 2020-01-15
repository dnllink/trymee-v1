/**
 * 
 */
package br.com.bonaldoapps.trymee.email;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;

import br.com.bonaldoapps.trymee.entity.Link;
import br.com.bonaldoapps.trymee.service.facade.PropertiesFacade;

/**
 * @author Daniel
 *
 */
@Stateless
public class SparkPostIntegrationFacade {

	private static final Logger LOGGER = Logger.getLogger("SparkPostIntegrationFacade");

	@Inject
	private PropertiesFacade emailProperties;

	public void sendEmailBySP(Link link) throws Exception {

		SparkPostEntity spe = new SparkPostEntity();

		Properties p;
		try {
			p = emailProperties.readProperties();
		} catch (IOException e) {
			LOGGER.info(e.getMessage());
			throw new Exception(
					"Não foi possível enviar o email, entre em contato com o atendimento em suporte@trymee.com.br");
		}

		spe.getContent().setFrom(p.getProperty(PropertiesFacade.FROM_PROPERTY));

		String subjectReplaced = p.getProperty(PropertiesFacade.SUBJECT_PROPERTY).replace("{nome}",
				link.getCandidate().getName());
		spe.getContent().setSubject(subjectReplaced);

		String contentReplaced = p.getProperty(PropertiesFacade.CONTENT_PROPERTY).replace("{link}", link.getUrl())
				.replace("{nome}", link.getCandidate().getName());
		spe.getContent().setText(contentReplaced);

		spe.getRecipients().add(new Recipient(link.getCandidate().getEmail()));

		Entity<SparkPostEntity> entity = Entity.json(spe);

		ClientBuilder.newClient().target("https://api.sparkpost.com/api/v1").path("transmissions").request()
				.header("Authorization", "0511ba011d011122a67955fe47761a22b6ca9c72")
				.header("Content-Type", "application/json").post(entity);

	}

	class SparkPostEntity {

		private Content content = new Content();
		private List<Recipient> recipients = new LinkedList<>();

		/**
		 * @return the content
		 */
		public Content getContent() {
			return content;
		}

		/**
		 * @param content
		 *            the content to set
		 */
		public void setContent(Content content) {
			this.content = content;
		}

		/**
		 * @return the recipients
		 */
		public List<Recipient> getRecipients() {
			return recipients;
		}

		/**
		 * @param recipients
		 *            the recipients to set
		 */
		public void setRecipients(List<Recipient> recipients) {
			this.recipients = recipients;
		}

	}

	class Content {

		private String from;
		private String subject;
		private String text;

		/**
		 * @return the from
		 */
		public String getFrom() {
			return from;
		}

		/**
		 * @param from
		 *            the from to set
		 */
		public void setFrom(String from) {
			this.from = from;
		}

		/**
		 * @return the subject
		 */
		public String getSubject() {
			return subject;
		}

		/**
		 * @param subject
		 *            the subject to set
		 */
		public void setSubject(String subject) {
			this.subject = subject;
		}

		/**
		 * @return the text
		 */
		public String getText() {
			return text;
		}

		/**
		 * @param text
		 *            the text to set
		 */
		public void setText(String text) {
			this.text = text;
		}

	}

	class Recipient {

		private String address;

		/**
		 * 
		 */
		public Recipient(String email) {

			this.address = email;

		}

		/**
		 * @return the address
		 */
		public String getAddress() {
			return address;
		}

		/**
		 * @param address
		 *            the address to set
		 */
		public void setAddress(String address) {
			this.address = address;
		}

	}

}
