package br.com.bonaldoapps.trymee.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "process")
public class Process implements Serializable {

	private static final Logger LOGGER = LogManager.getLogManager().getLogger("Process");

	/**
	 * 
	 */
	private static final long serialVersionUID = 2894390338404840655L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_process")
	private Long id;

	@Column(name = "ds_process", length = 200)
	private String description;

	@Column(name = "dt_start")
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "dd/MM/yyyy", locale = "pt-BR")
	private Date startDate;

	@Column(name = "dt_end")
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "dd/MM/yyyy", locale = "pt-BR")
	private Date endDate;

	@Column(name = "fg_active")
	private Boolean active = true;

	@ManyToOne
	@JoinColumn(name = "id_test")
	private Test test;

	@ManyToMany
	@JoinTable(name = "process_candidates", joinColumns = @JoinColumn(name = "id_process"), inverseJoinColumns = @JoinColumn(name = "id_candidate"))
	private List<Candidate> candidates = new LinkedList<Candidate>();

	@OneToMany(mappedBy = "process")
	private List<Link> links;

	@ManyToOne
	@JoinColumn(name = "id_user")
	private User user;

	@Column(name = "dt_creation")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate = new Date();

	@Transient
	private Boolean loadedLinks = false;

	@Transient
	private Boolean loadedCandidates = false;

	@Transient
	private String strStartDate;

	public Boolean hasTest() {

		if (this.test == null) {
			return false;
		} else {
			return true;
		}

	}

	public Boolean hasCandidates() {

		if (this.candidates == null)
			return false;

		if (this.candidates.size() < 1)
			return false;

		return true;

	}

	public void updateStartDateFromString() throws Exception {

		if (this.strStartDate == null || this.strStartDate.isEmpty()) {
			return;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			Date startDate = sdf.parse(this.strStartDate);
			this.startDate = startDate;
		} catch (ParseException e) {
			LOGGER.warning("Nao foi possivel parsear a data: " + this.strStartDate);
			throw new Exception("Data de início de processo não é válida.");
		}

	}

	public Long getId() {
		return id;
	}

	public void setId(Long idProcess) {
		this.id = idProcess;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public List<Candidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<Candidate> candidates) {
		this.candidates = candidates;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
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
	 * @param c
	 */
	public void addCandidate(Candidate c) {

		getCandidates().add(c);
		c.getProcesses().add(this);

	}

	/**
	 * @return the loadedCandidates
	 */
	public Boolean getLoadedCandidates() {
		return loadedCandidates;
	}

	/**
	 * @param loadedCandidates
	 *            the loadedCandidates to set
	 */
	public void setLoadedCandidates(Boolean loadedCandidates) {
		this.loadedCandidates = loadedCandidates;
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
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the strStartDate
	 */
	public String getStrStartDate() {
		return strStartDate;
	}

	/**
	 * @param strStartDate
	 *            the strStartDate to set
	 */
	public void setStrStartDate(String strStartDate) {
		this.strStartDate = strStartDate;
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

}
