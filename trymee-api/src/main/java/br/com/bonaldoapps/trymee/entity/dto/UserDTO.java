/**
 * 
 */
package br.com.bonaldoapps.trymee.entity.dto;

import java.util.Date;

import br.com.bonaldoapps.trymee.entity.User;

/**
 * @author Daniel
 *
 */
public class UserDTO {

	private Long id;

	private String name;

	private String email;

	private String password;

	private Date dateCreation;

	/**
	 * @param user
	 */
	public UserDTO(User model) {

		if (model == null)
			return;

		if (model.getId() != null)
			this.id = model.getId();

		if (model.getName() != null)
			this.name = model.getName();

		if (model.getEmail() != null)
			this.email = model.getEmail();

		if (model.getDateCreation() != null)
			this.dateCreation = model.getDateCreation();

	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the dateCreation
	 */
	public Date getDateCreation() {
		return dateCreation;
	}

	/**
	 * @param dateCreation
	 *            the dateCreation to set
	 */
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

}
