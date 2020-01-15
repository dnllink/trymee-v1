package br.com.bonaldo.trymee.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "alternatives")
public class Alternative implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6331483097957137944L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_alternative")
	private Long id;

	@Column(name = "ds_alternative", length = 1000)
	private String description;

	@Column(name = "fg_correct")
	private Boolean correct;

	@ManyToOne
	@JoinColumn(name = "id_question")
	private Question question;

	public Alternative() {
	}

	/**
	 * @param c
	 */
	public Alternative(Long c) {

		this.setId(c);

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
	 * @return the correct
	 */
	public Boolean isCorrect() {
		return correct;
	}

	/**
	 * @param correct
	 *            the correct to set
	 */
	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}

	/**
	 * @param question
	 *            the question to set
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}

}
