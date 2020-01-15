/**
 * 
 */
package br.com.bonaldoapps.trymee.entity.dto;

import br.com.bonaldoapps.trymee.entity.Plan;

/**
 * @author Daniel
 *
 */
public class PlanDTO {

	private Long id;

	private String name;

	private String description;

	private String type;

	private Long period;

	private Long testQuantity;

	private Double value;

	private Boolean active;

	/**
	 * @param plan
	 */
	public PlanDTO(Plan model) {

		if (model == null)
			return;

		if (model.getId() != null)
			this.id = model.getId();

		if (model.getName() != null)
			this.name = model.getName();

		if (model.getDescription() != null)
			this.description = model.getDescription();

		if (model.getType() != null)
			this.type = model.getType();

		if (model.getPeriod() != null)
			this.period = model.getPeriod();

		if (model.getTestQuantity() != null)
			this.testQuantity = model.getTestQuantity();

		if (model.getValue() != null)
			this.value = model.getValue();

		if (model.getActive() != null)
			this.active = model.getActive();

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
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the period
	 */
	public Long getPeriod() {
		return period;
	}

	/**
	 * @param period
	 *            the period to set
	 */
	public void setPeriod(Long period) {
		this.period = period;
	}

	/**
	 * @return the testQuantity
	 */
	public Long getTestQuantity() {
		return testQuantity;
	}

	/**
	 * @param testQuantity
	 *            the testQuantity to set
	 */
	public void setTestQuantity(Long testQuantity) {
		this.testQuantity = testQuantity;
	}

	/**
	 * @return the value
	 */
	public Double getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(Double value) {
		this.value = value;
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
