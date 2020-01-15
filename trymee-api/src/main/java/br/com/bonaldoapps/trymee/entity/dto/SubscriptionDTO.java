/**
 * 
 */
package br.com.bonaldoapps.trymee.entity.dto;

import java.util.Date;

import br.com.bonaldoapps.trymee.entity.Subscription;

/**
 * @author Daniel
 *
 */
public class SubscriptionDTO {

	private Long id;

	private Date dateSubscription;

	private Date dateEndPeriod;

	private UserDTO user;

	private PlanDTO plan;

	private Boolean active;

	private Long testsLeft;

	/**
	 * @param subscription
	 */
	public SubscriptionDTO(Subscription model) {

		if (model == null)
			return;

		if (model.getId() != null)
			this.id = model.getId();

		if (model.getDateSubscription() != null)
			this.dateSubscription = model.getDateSubscription();

		if (model.getDateEndPeriod() != null)
			this.dateEndPeriod = model.getDateEndPeriod();

		if (model.getUser() != null)
			this.user = new UserDTO(model.getUser());

		if (model.getPlan() != null)
			this.plan = new PlanDTO(model.getPlan());

		if (model.getActive() != null)
			this.active = model.getActive();

		if (model.getTestsLeft() != null)
			this.testsLeft = model.getTestsLeft();

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
	 * @return the dateSubscription
	 */
	public Date getDateSubscription() {
		return dateSubscription;
	}

	/**
	 * @param dateSubscription
	 *            the dateSubscription to set
	 */
	public void setDateSubscription(Date dateSubscription) {
		this.dateSubscription = dateSubscription;
	}

	/**
	 * @return the dateEndPeriod
	 */
	public Date getDateEndPeriod() {
		return dateEndPeriod;
	}

	/**
	 * @param dateEndPeriod
	 *            the dateEndPeriod to set
	 */
	public void setDateEndPeriod(Date dateEndPeriod) {
		this.dateEndPeriod = dateEndPeriod;
	}

	/**
	 * @return the user
	 */
	public UserDTO getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(UserDTO user) {
		this.user = user;
	}

	/**
	 * @return the plan
	 */
	public PlanDTO getPlan() {
		return plan;
	}

	/**
	 * @param plan
	 *            the plan to set
	 */
	public void setPlan(PlanDTO plan) {
		this.plan = plan;
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
	 * @return the testsLeft
	 */
	public Long getTestsLeft() {
		return testsLeft;
	}

	/**
	 * @param testsLeft
	 *            the testsLeft to set
	 */
	public void setTestsLeft(Long testsLeft) {
		this.testsLeft = testsLeft;
	}

}
