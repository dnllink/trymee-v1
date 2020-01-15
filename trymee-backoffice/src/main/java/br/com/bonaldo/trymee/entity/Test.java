package br.com.bonaldo.trymee.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "test")
public class Test implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7633652179560622642L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_test")
	private Long id;

	@Column(name = "nm_test", length = 100)
	private String name;

	@Column(name = "ds_test", length = 1000)
	private String description;

	@ManyToOne
	@JoinColumn(name = "id_level")
	private Level level;

	@Column(name = "qt_question")
	private Long questionQuantity;

	@Column(name = "fg_active")
	private Boolean active = true;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "test_categories", joinColumns = @JoinColumn(name = "id_test"), inverseJoinColumns = @JoinColumn(name = "id_category"))
	private List<Category> categories = new ArrayList<Category>();

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "test_question", joinColumns = @JoinColumn(name = "id_test"), inverseJoinColumns = @JoinColumn(name = "id_question"))
	private List<Question> questions = new ArrayList<Question>();

	@ManyToOne
	@JoinColumn(name = "id_user")
	private User user;

	@Column(name = "dt_creation")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate = new Date();

	@Transient
	private Boolean loadedCategories = true;

	@Transient
	private Boolean loadedQuestions = false;

	@Transient
	private Long idProcess;

	public Test() {
	}

	public void addQuestion(Question question) {
		question.addTest(this);
		this.getQuestions().add(question);
	}

	public void addCategory(Category category) {

		this.getCategories().add(category);

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
	public Level getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(Level level) {
		this.level = level;
	}

	/**
	 * @return the active
	 */
	public Boolean isActive() {
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
	public List<Question> getQuestions() {
		return questions;
	}

	/**
	 * @param questions
	 *            the questions to set
	 */
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
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

	/**
	 * @return the loadedQuestions
	 */
	public Boolean getLoadedQuestions() {
		return loadedQuestions;
	}

	/**
	 * @param loadedQuestions
	 *            the loadedQuestions to set
	 */
	public void setLoadedQuestions(Boolean loadedQuestions) {
		this.loadedQuestions = loadedQuestions;
	}

	/**
	 * @return the active
	 */
	public Boolean getActive() {
		return active;
	}

	/**
	 * @return the loadedCategories
	 */
	public Boolean getLoadedCategories() {
		return loadedCategories;
	}

	/**
	 * @param loadedCategories
	 *            the loadedCategories to set
	 */
	public void setLoadedCategories(Boolean loadedCategories) {
		this.loadedCategories = loadedCategories;
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
	 * @return the idProcess
	 */
	public Long getIdProcess() {
		return idProcess;
	}

	/**
	 * @param idProcess
	 *            the idProcess to set
	 */
	public void setIdProcess(Long idProcess) {
		this.idProcess = idProcess;
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
