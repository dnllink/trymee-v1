package br.com.bonaldoapps.trymee.entity.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.bonaldoapps.trymee.entity.Category;
import br.com.bonaldoapps.trymee.entity.Question;
import br.com.bonaldoapps.trymee.entity.Test;

public class TestDTO {

	private Long id;
	private String name;
	private String description;
	private LevelDTO level;
	private Boolean active = true;
	private Long questionQuantity;
	private List<CategoryDTO> categories;
	private List<QuestionDTO> questions = new ArrayList<QuestionDTO>();

	/**
	 * 
	 */
	public TestDTO() {
	}

	/**
	 * @param t
	 */
	public TestDTO(Test model) {

		if (model == null) {
			return;
		}

		if (model.getId() != null) {
			this.setId(model.getId());
		}

		if (model.getActive() != null) {
			this.setActive(model.getActive());
		}

		if (model.getDescription() != null) {
			this.setDescription(model.getDescription());
		}

		if (model.getName() != null) {
			this.setName(model.getName());
		}

		if (model.getLevel() != null) {
			this.setLevel(new LevelDTO(model.getLevel()));
		}

		if (model.getQuestionQuantity() != null) {
			this.setQuestionQuantity(model.getQuestionQuantity());
		}

		if (model.getLoadedQuestions() && model.getQuestions().size() > 0) {

			this.setQuestions(new ArrayList<QuestionDTO>());

			for (Question q : model.getQuestions()) {

				this.getQuestions().add(new QuestionDTO(q));

			}

		}

		if (model.getLoadedCategories() && !model.getCategories().isEmpty()) {

			this.setCategories(new ArrayList<CategoryDTO>());

			for (Category c : model.getCategories()) {

				this.getCategories().add(new CategoryDTO(c));

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
	 * @return the questions
	 */
	public List<QuestionDTO> getQuestions() {
		return questions;
	}

	/**
	 * @param questions
	 *            the questions to set
	 */
	public void setQuestions(List<QuestionDTO> questions) {
		this.questions = questions;
	}

	public List<CategoryDTO> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryDTO> categories) {
		this.categories = categories;
	}

	/**
	 * @return the questionQuantity
	 */
	public Long getQuestionQuantity() {
		return questionQuantity;
	}

	/**
	 * @param questionQuantity
	 *            the questionQuantity to set
	 */
	public void setQuestionQuantity(Long questionQuantity) {
		this.questionQuantity = questionQuantity;
	}

}
