/**
 * 
 */
package br.com.bonaldoapps.trymee.entity.dto;

import java.io.Serializable;

/**
 * @author Daniel
 *
 */
public class ProcessSummaryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3821405318096807014L;

	private Long id;
	private String description;
	private Long qtCandidates;
	private Long qtSent;
	private Long qtAnswered;
	private Long qtPending;
	private Long qtCorrected;
	private Double averageRating;
	private Long idTest;
	private Boolean active;

	/**
	 * 
	 */
	public ProcessSummaryDTO() {
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
	 * @return the qtCandidates
	 */
	public Long getQtCandidates() {
		return qtCandidates;
	}

	/**
	 * @param qtCandidates
	 *            the qtCandidates to set
	 */
	public void setQtCandidates(Long qtCandidates) {
		this.qtCandidates = qtCandidates;
	}

	/**
	 * @return the qtSent
	 */
	public Long getQtSent() {
		return qtSent;
	}

	/**
	 * @param qtSent
	 *            the qtSent to set
	 */
	public void setQtSent(Long qtSent) {
		this.qtSent = qtSent;
	}

	/**
	 * @return the qtAnswered
	 */
	public Long getQtAnswered() {
		return qtAnswered;
	}

	/**
	 * @param qtAnswered
	 *            the qtAnswered to set
	 */
	public void setQtAnswered(Long qtAnswered) {
		this.qtAnswered = qtAnswered;
	}

	/**
	 * @return the qtPending
	 */
	public Long getQtPending() {
		return qtPending;
	}

	/**
	 * @param qtPending
	 *            the qtPending to set
	 */
	public void setQtPending(Long qtPending) {
		this.qtPending = qtPending;
	}

	/**
	 * @return the qtCorrected
	 */
	public Long getQtCorrected() {
		return qtCorrected;
	}

	/**
	 * @param qtCorrected
	 *            the qtCorrected to set
	 */
	public void setQtCorrected(Long qtCorrected) {
		this.qtCorrected = qtCorrected;
	}

	/**
	 * @return the averageRating
	 */
	public Double getAverageRating() {
		return averageRating;
	}

	/**
	 * @param averageRating
	 *            the averageRating to set
	 */
	public void setAverageRating(Double averageRating) {
		this.averageRating = averageRating;
	}

	/**
	 * @return the idTest
	 */
	public Long getIdTest() {
		return idTest;
	}

	/**
	 * @param idTest
	 *            the idTest to set
	 */
	public void setIdTest(Long idTest) {
		this.idTest = idTest;
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

}
