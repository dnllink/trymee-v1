package br.com.bonaldoapps.trymee.entity.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.bonaldoapps.trymee.entity.Alternative;
import br.com.bonaldoapps.trymee.entity.Question;
import br.com.bonaldoapps.trymee.entity.Test;
import br.com.bonaldoapps.trymee.entity.enums.QuestionTypeEnum;

public class QuestionDTO {

	private Long id;
	private String description;
	private QuestionTypeEnum type;
	private CategoryDTO category;
	private LevelDTO level;
	private String answer;
	private String imageLink;
	private Boolean active;
	private List<AlternativeDTO> alternatives;
	private List<TestDTO> tests;

	public QuestionDTO() {
	}

	public QuestionDTO(Question model) {

		if (model == null) {
			return;
		}

		if (model.isActive() != null) {
			this.setActive(model.isActive());
		}

		if (model.getAnswer() != null) {
			this.setAnswer(model.getAnswer());
		}

		if (model.getDescription() != null) {
			this.setDescription(model.getDescription());
		}

		if (model.getId() != null) {
			this.setId(model.getId());
		}

		if (model.getImageLink() != null) {
			this.setImageLink(model.getImageLink());
		}

		if (model.getType() != null) {
			this.setType(model.getType());
		}

		if (model.getCategory() != null) {
			this.setCategory(new CategoryDTO(model.getCategory()));
		}

		if (model.getLevel() != null) {
			this.setLevel(new LevelDTO(model.getLevel()));
		}

		if (model.getAlternatives() != null && model.getAlternatives().size() > 0) {

			this.setAlternatives(new ArrayList<AlternativeDTO>());

			for (Alternative a : model.getAlternatives()) {

				this.getAlternatives().add(new AlternativeDTO(a));

			}

		}

		if (model.getLoadedTests() && !model.getTests().isEmpty()) {

			this.setTests(new ArrayList<TestDTO>());

			for (Test t : model.getTests()) {

				this.getTests().add(new TestDTO(t));

			}

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
	 * @return the type
	 */
	public QuestionTypeEnum getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(QuestionTypeEnum type) {
		this.type = type;
	}

	/**
	 * @return the category
	 */
	public CategoryDTO getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(CategoryDTO category) {
		this.category = category;
	}

	/**
	 * @return the level
	 */
	public LevelDTO getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(LevelDTO level) {
		this.level = level;
	}

	/**
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * @param answer
	 *            the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/**
	 * @return the imageLink
	 */
	public String getImageLink() {
		return imageLink;
	}

	/**
	 * @param imageLink
	 *            the imageLink to set
	 */
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
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
	 * @return the alternatives
	 */
	public List<AlternativeDTO> getAlternatives() {
		return alternatives;
	}

	/**
	 * @param alternatives
	 *            the alternatives to set
	 */
	public void setAlternatives(List<AlternativeDTO> alternatives) {
		this.alternatives = alternatives;
	}

	/**
	 * @return the tests
	 */
	public List<TestDTO> getTests() {
		return tests;
	}

	/**
	 * @param tests
	 *            the tests to set
	 */
	public void setTests(List<TestDTO> tests) {
		this.tests = tests;
	}

}
