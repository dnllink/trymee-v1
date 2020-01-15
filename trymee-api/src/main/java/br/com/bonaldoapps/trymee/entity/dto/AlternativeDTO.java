package br.com.bonaldoapps.trymee.entity.dto;

import br.com.bonaldoapps.trymee.entity.Alternative;

public class AlternativeDTO {

	private Long id;
	private String description;
	private Boolean correct;

	/**
	 * 
	 */
	public AlternativeDTO() {
	}

	/**
	 * @param a
	 */
	public AlternativeDTO(Alternative model) {

		if (model == null) {
			return;
		}

		if (model.getDescription() != null) {
			this.setDescription(model.getDescription());
		}

		if (model.isCorrect() != null) {
			this.setCorrect(model.isCorrect());
		}

		if (model.getId() != null) {
			this.setId(model.getId());
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
	 * @return the isCorrect
	 */
	public Boolean isCorrect() {
		return correct;
	}

	/**
	 * @param isCorrect
	 *            the isCorrect to set
	 */
	public void setCorrect(Boolean isCorrect) {
		this.correct = isCorrect;
	}

}
