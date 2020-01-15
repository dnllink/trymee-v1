/**
 * 
 */
package br.com.bonaldoapps.trymee.email.entity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Daniel
 *
 */
public class SPWrapper {

	private Content content = new Content();
	private List<Recipient> recipients = new LinkedList<>();
	private Map<String, String> substitution_data = new HashMap<String, String>();

	/**
	 * @return the content
	 */

	/**
	 * 
	 */
	public SPWrapper() {
	}

	public void addRecipient(String name, String email) {

		Recipient rec = new Recipient(email, name);
		this.recipients.add(rec);

	}

	public void addData(String key, String value) {

		this.substitution_data.put(key, value);

	}

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
	 * @return the substitution_data
	 */
	public Map<String, String> getSubstitution_data() {
		return substitution_data;
	}

	/**
	 * @param substitution_data
	 *            the substitution_data to set
	 */
	public void setSubstitution_data(Map<String, String> substitution_data) {
		this.substitution_data = substitution_data;
	}

	/**
	 * @return the recipients
	 */
	public List<Recipient> getRecipients() {
		return recipients;
	}

	/**
	 * @param recipients the recipients to set
	 */
	public void setRecipients(List<Recipient> recipients) {
		this.recipients = recipients;
	}

}
