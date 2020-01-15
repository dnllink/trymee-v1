/**
 * 
 */
package br.com.bonaldoapps.trymee.email.interf;

import java.util.List;
import java.util.Map;

import br.com.bonaldoapps.trymee.email.entity.Recipient;

/**
 * @author Daniel
 *
 */
public interface TMEmail {

	public List<Recipient> recipients();

	public String template();

	public Map<String, String> data();

	public String from();

	public String fromName();

	public String subject();

}
