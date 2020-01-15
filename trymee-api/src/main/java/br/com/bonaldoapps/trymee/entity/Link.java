package br.com.bonaldoapps.trymee.entity;

import java.io.Serializable;
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
@Table(name = "links")
public class Link implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8811147084768949651L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_link")
	private Long id;

	@Column(name = "cd_link", length = 50)
	private String code;

	@Column(name = "ds_link", length = 200)
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_first_sent")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm", locale = "pt-BR")
	private Date dtFirstSent;

	@Column(name = "ds_url", length = 200)
	private String url;

	@Column(name = "fg_active")
	private Boolean active = true;

	@ManyToOne
	@JoinColumn(name = "id_process")
	private Process process;

	@ManyToOne
	@JoinColumn(name = "id_candidate")
	private Candidate candidate;

	@ManyToOne
	@JoinColumn(name = "id_user")
	private User user;

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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the process
	 */
	public Process getProcess() {
		return process;
	}

	/**
	 * @param process
	 *            the process to set
	 */
	public void setProcess(Process process) {
		this.process = process;
	}

	/**
	 * @return the candidate
	 */
	public Candidate getCandidate() {
		return candidate;
	}

	/**
	 * @param candidate
	 *            the candidate to set
	 */
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	/**
	 * @return the active
	 */
	public Boolean getActive() {
		return active;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the dtFirstSent
	 */
	public Date getDtFirstSent() {
		return dtFirstSent;
	}

	/**
	 * @param dtFirstSent
	 *            the dtFirstSent to set
	 */
	public void setDtFirstSent(Date dtFirstSent) {
		this.dtFirstSent = dtFirstSent;
	}
}
