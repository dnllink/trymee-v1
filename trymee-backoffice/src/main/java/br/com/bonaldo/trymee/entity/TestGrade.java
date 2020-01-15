package br.com.bonaldo.trymee.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "test_grades")
public class TestGrade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2720353605561041817L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_test_grade")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_candidate")
	private Candidate candidate;

	@ManyToOne
	@JoinColumn(name = "id_test")
	private Test test;

	@ManyToOne
	@JoinColumn(name = "id_process")
	private Process process;

	@OneToOne
	@JoinColumn(name = "id_link")
	private Link link;

	@Column(name = "vl_rating")
	private Double rating;

	@Column(name = "fg_partial")
	private Boolean partial;

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
	 * @return the test
	 */
	public Test getTest() {
		return test;
	}

	/**
	 * @param test
	 *            the test to set
	 */
	public void setTest(Test test) {
		this.test = test;
	}

	/**
	 * @return the link
	 */
	public Link getLink() {
		return link;
	}

	/**
	 * @param link
	 *            the link to set
	 */
	public void setLink(Link link) {
		this.link = link;
	}

	/**
	 * @return the rating
	 */
	public Double getRating() {
		return rating;
	}

	/**
	 * @param rating
	 *            the rating to set
	 */
	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

	public Boolean getPartial() {
		return partial;
	}

	public void setPartial(Boolean partial) {
		this.partial = partial;
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
}
