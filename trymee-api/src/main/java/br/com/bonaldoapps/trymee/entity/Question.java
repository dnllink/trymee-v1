package br.com.bonaldoapps.trymee.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.bonaldoapps.trymee.entity.enums.QuestionTypeEnum;

@Entity
@Table(name = "questions")
public class Question implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4757793179238107895L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_question")
	private Long id;

	@Column(name = "ds_question", length = 1000)
	private String description;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "cd_type")
	private QuestionTypeEnum type;

	@ManyToOne
	@JoinColumn(name = "id_category")
	private Category category;

	@ManyToOne
	@JoinColumn(name = "id_level")
	private Level level;

	@Column(name = "ds_answer", length = 1000)
	private String answer;

	@Column(name = "nm_link", length = 200)
	private String imageLink;

	@Column(name = "fg_active")
	private Boolean active = true;

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE,
			CascadeType.MERGE }, fetch = FetchType.EAGER, mappedBy = "question")
	private List<Alternative> alternatives = new ArrayList<>();

	@ManyToMany(mappedBy = "questions")
	private List<Test> tests = new ArrayList<Test>();

	@ManyToOne
	@JoinColumn(name = "id_user")
	private User user;

	@Column(name = "dt_creation")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate = new Date();

	@Transient
	private Boolean loadedTests = false;

	public Question() {
	}

	public void addAlternative(Alternative a) {
		a.setQuestion(this);
		this.alternatives.add(a);
	}

	public void addTest(Test test) {
		this.tests.add(test);
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
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
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
	 * @return the alternatives
	 */
	public List<Alternative> getAlternatives() {
		return alternatives;
	}

	/**
	 * @param alternatives
	 *            the alternatives to set
	 */
	public void setAlternatives(List<Alternative> alternatives) {
		this.alternatives = alternatives;
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
	 * @param tests
	 *            the tests to set
	 */
	public void setTests(List<Test> tests) {
		this.tests = tests;
	}

	public List<Test> getTests() {
		return tests;
	}

	/**
	 * @return the loadedTests
	 */
	public Boolean getLoadedTests() {
		return loadedTests;
	}

	/**
	 * @param loadedTests
	 *            the loadedTests to set
	 */
	public void setLoadedTests(Boolean loadedTests) {
		this.loadedTests = loadedTests;
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
