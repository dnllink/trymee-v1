/**
 * 
 */
package br.com.bonaldoapps.trymee.email.entity;

/**
 * @author Daniel
 *
 */
public class Recipient {

	private String address;
	private String name;

	/**
	 * 
	 */
	public Recipient(String email, String name) {

		this.address = email;
		this.name = name;

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

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
