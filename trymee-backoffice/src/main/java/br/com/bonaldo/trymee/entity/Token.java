package br.com.bonaldo.trymee.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tokens")
public class Token implements Serializable {

	private static final Integer EXPIRATION_HOURS = 24;

	/**
	 * 
	 */
	private static final long serialVersionUID = -8890084372643353208L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_token")
	private Long id;

	@Column(name = "ds_token", length = 200)
	private String token;

	@ManyToOne
	@JoinColumn(name = "id_user")
	private User user;

	@Column(name = "dt_creation")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", locale = "GMT-2")
	private Date dateCreation;

	@Column(name = "ds_ip", length = 50)
	private String ip;

	@Column(name = "fg_active")
	private Boolean active;

	public Token() {
	}

	public Boolean isValidToken(Calendar cal) {

		Calendar expirationTime = Calendar.getInstance();
		expirationTime.setTime(this.getDateCreation());

		expirationTime.add(Calendar.HOUR_OF_DAY, EXPIRATION_HOURS);

		if (cal.getTime().before(expirationTime.getTime())) {
			return true;
		}

		return false;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
