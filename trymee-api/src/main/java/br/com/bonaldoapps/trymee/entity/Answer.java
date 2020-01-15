/**
 * 
 */
package br.com.bonaldoapps.trymee.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Daniel
 *
 */
@Entity
@Table(name = "answers")
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_answer")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_question")
	private Question question;

	@Column(name = "ds_answer", length = 1000)
	private String answerText;

	@ManyToOne
	@JoinColumn(name = "id_alternative")
	private Alternative answerAlternative;

	@ManyToOne
	@JoinColumn(name = "id_process")
	private Process process;

	@ManyToOne
	@JoinColumn(name = "id_candidate")
	private Candidate candidate;

	@Column(name = "fg_correct")
	private Boolean correct;

	@ManyToOne
	@JoinColumn(name = "id_user")
	private User user;

	public Answer() {
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
	 * @return the question
	 */
	public Question getQuestion() {
		return question;
	}

	/**
	 * @param question
	 *            the question to set
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}

	/**
	 * @return the answerText
	 */
	public String getAnswerText() {
		return answerText;
	}

	/**
	 * @param answerText
	 *            the answerText to set
	 */
	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	/**
	 * @return the answerAlternative
	 */
	public Alternative getAnswerAlternative() {
		return answerAlternative;
	}

	/**
	 * @param answerAlternative
	 *            the answerAlternative to set
	 */
	public void setAnswerAlternative(Alternative answerAlternative) {
		this.answerAlternative = answerAlternative;
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

	public Boolean getCorrect() {
		return correct;
	}

	public void setCorrect(Boolean correct) {
		this.correct = correct;
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
