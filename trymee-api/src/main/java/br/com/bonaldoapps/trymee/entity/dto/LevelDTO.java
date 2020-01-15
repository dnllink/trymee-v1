package br.com.bonaldoapps.trymee.entity.dto;

import br.com.bonaldoapps.trymee.entity.Level;

public class LevelDTO {

	private Long id;
	private String description;
	private String code;

	/**
	 * 
	 */
	public LevelDTO() {
	}

	/**
	 * @param level
	 */
	public LevelDTO(Level model) {

		if (model == null) {
			return;
		}

		if (model.getId() != null) {
			this.setId(model.getId());
		}

		if (model.getDescription() != null) {
			this.setDescription(model.getDescription());
		}

		if (model.getCode() != null) {
			this.setCode(model.getCode());
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

}
