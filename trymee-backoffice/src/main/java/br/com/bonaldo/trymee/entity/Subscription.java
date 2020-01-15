package br.com.bonaldo.trymee.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "subscriptions")
public class Subscription implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3880457756045274982L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_subscripton")
	private Long id;

	@Column(name = "dt_subscription")
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "dd/MM/yyyy", locale = "GMT-2")
	private Date dateSubscription;

	@Column(name = "dt_end_period")
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "dd/MM/yyyy", locale = "GMT-2")
	private Date dateEndPeriod;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_user")
	private User user;

	@ManyToOne
	@JoinColumn(name = "id_plan")
	private Plan plan;

	@Column(name = "fg_active")
	private Boolean active;

	@Column(name = "qt_tests_left")
	private Long testsLeft;

	public Subscription() {
	}

	public Subscription(User user, Plan plan) {

		Date dataAtual = new Date();

		this.setUser(user);
		this.setPlan(plan);
		this.setDateSubscription(dataAtual);
		this.setActive(true);

		Calendar cal = Calendar.getInstance();
		cal.setTime(dataAtual);

		cal.add(Calendar.DAY_OF_YEAR, plan.getPeriod().intValue());

		this.setDateEndPeriod(cal.getTime());

		if (Plan.QUANTITY_PLAN.equals(plan.getType())) {
			this.setTestsLeft(plan.getTestQuantity());
		}

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateSubscription() {
		return dateSubscription;
	}

	public void setDateSubscription(Date dateSubscription) {
		this.dateSubscription = dateSubscription;
	}

	public Date getDateEndPeriod() {
		return dateEndPeriod;
	}

	public void setDateEndPeriod(Date dateEndPeriod) {
		this.dateEndPeriod = dateEndPeriod;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Long getTestsLeft() {
		return testsLeft;
	}

	public void setTestsLeft(Long testsLeft) {
		this.testsLeft = testsLeft;
	}

}
