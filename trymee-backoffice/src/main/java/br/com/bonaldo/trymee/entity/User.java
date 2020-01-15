package br.com.bonaldo.trymee.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "users")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9206869009182716639L;

	public static final String DEFAULT_PASSWORD = "trymee@Online";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private Long id;

	@Column(name = "nm_user", length = 50)
	private String name;

	@Column(name = "ds_email", length = 100)
	private String email;

	@Column(name = "ds_password", length = 200)
	private String password;
	
	@Column(name = "ds_telephone", length = 20)
	private String telephone;

	@Column(name = "dt_creation")
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "dd/MM/yyyy", locale = "GMT-2")
	private Date dateCreation;

	public User() {
	}

	public User(String name, String email) {

		Calendar dataAtual = Calendar.getInstance();

		this.setName(name);
		this.setEmail(email);
		this.setDateCreation(dataAtual.getTime());
		// this.setPassword(DEFAULT_PASSWORD + dataAtual.get(Calendar.YEAR));

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}
