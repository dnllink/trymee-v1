package br.com.bonaldoapps.trymee.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "candidates")
public class Candidate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3797254926644105389L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_candidate")
	private Long id;

	@Column(name = "nm_candidate", length = 100)
	private String name;

	@Column(name = "ds_email", length = 200)
	private String email;

	@Column(name = "fg_active")
	private Boolean active = true;

	@ManyToMany(mappedBy = "candidates")
	private List<Process> processes = new LinkedList<Process>();

	@OneToMany(mappedBy = "candidate")
	private List<TestGrade> testGrades;

	@OneToMany(mappedBy = "candidate")
	private List<Link> links;

	@ManyToOne
	@JoinColumn(name = "id_user")
	private User user;

	@Column(name = "dt_creation")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate = new Date();

	@Transient
	private Boolean loadedProcesses = false;

	@Transient
	private Boolean loadedGrades = false;

	@Transient
	private Boolean loadedLinks = false;

	@Transient
	private Boolean link;
	
	@Transient
	private String linkUrl;

	public Candidate() {
	}

	public Candidate(Long id, String name, String email, Boolean activeLink, String linkUrl) {

		this.id = id;
		this.name = name;
		this.email = email;
		this.linkUrl = linkUrl;

		if (activeLink != null) {
			this.link = activeLink;
		}

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
	 * @return the processes
	 */
	public List<Process> getProcesses() {
		return processes;
	}

	/**
	 * @param processes
	 *            the processes to set
	 */
	public void setProcesses(List<Process> processes) {
		this.processes = processes;
	}

	/**
	 * @return the testGrades
	 */
	public List<TestGrade> getTestGrades() {
		return testGrades;
	}

	/**
	 * @param testGrades
	 *            the testGrades to set
	 */
	public void setTestGrades(List<TestGrade> testGrades) {
		this.testGrades = testGrades;
	}

	/**
	 * @return the links
	 */
	public List<Link> getLinks() {
		return links;
	}

	/**
	 * @param links
	 *            the links to set
	 */
	public void setLinks(List<Link> links) {
		this.links = links;
	}

	/**
	 * @return the loadedProcesses
	 */
	public Boolean getLoadedProcesses() {
		return loadedProcesses;
	}

	/**
	 * @param loadedProcesses
	 *            the loadedProcesses to set
	 */
	public void setLoadedProcesses(Boolean loadedProcesses) {
		this.loadedProcesses = loadedProcesses;
	}

	/**
	 * @return the loadedGrades
	 */
	public Boolean getLoadedGrades() {
		return loadedGrades;
	}

	/**
	 * @param loadedGrades
	 *            the loadedGrades to set
	 */
	public void setLoadedGrades(Boolean loadedGrades) {
		this.loadedGrades = loadedGrades;
	}

	/**
	 * @return the loadedLinks
	 */
	public Boolean getLoadedLinks() {
		return loadedLinks;
	}

	/**
	 * @param loadedLinks
	 *            the loadedLinks to set
	 */
	public void setLoadedLinks(Boolean loadedLinks) {
		this.loadedLinks = loadedLinks;
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
	 * @return the link
	 */
	public Boolean getLink() {
		return link;
	}

	/**
	 * @param link
	 *            the link to set
	 */
	public void setLink(Boolean link) {
		this.link = link;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate
	 *            the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the linkUrl
	 */
	public String getLinkUrl() {
		return linkUrl;
	}

	/**
	 * @param linkUrl the linkUrl to set
	 */
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

}
