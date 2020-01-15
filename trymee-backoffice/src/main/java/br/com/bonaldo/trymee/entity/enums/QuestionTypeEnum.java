package br.com.bonaldo.trymee.entity.enums;

import java.io.Serializable;

public enum QuestionTypeEnum implements Serializable {

	DISCURSIVA(1l, "Discursiva"), MULTIPLA_ESCOLHA(2l, "Múltipla escolha");

	private QuestionTypeEnum(Long id, String description) {
		this.id = id;
		this.description = description;
	}

	private Long id;
	private String description;

	public static QuestionTypeEnum getById(int id) {
		for (QuestionTypeEnum en : QuestionTypeEnum.values()) {
			if (id == en.getId())
				return en;
		}
		return null;
	}

	public static QuestionTypeEnum getByDescription(String desc) {

		if (desc == null) {
			return null;
		}

		for (QuestionTypeEnum en : QuestionTypeEnum.values()) {
			if (desc.equals(en.name()))
				return en;
		}
		return null;
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

}
