/**
 * 
 */
package br.com.bonaldoapps.trymee.entity.dto;

/**
 * @author Daniel
 *
 */
public class Credentials {

	private String user;
	private String pass;

	/**
	 * 
	 */
	public Credentials() {
	}

	public Credentials(String user, String pass) {
		this.user = user;
		this.pass = pass;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @param pass
	 *            the pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

}
