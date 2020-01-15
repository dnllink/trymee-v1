package br.com.bonaldoapps.trymee.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "plans")
public class Plan implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2160501584448324275L;

	public static final String FREE_PLAN = "FREE_PLAN";

	public static final String QUANTITY_PLAN = "Q";

	public static final Long QUANTITY_1_TEST = 1L;
	public static final Long QUANTITY_3_TEST = 3L;
	public static final Long QUANTITY_10_TEST = 10L;

	public static final String PERIOD_PLAN = "P";

	public static final Long PERIOD_1_MONTH = 1L;
	public static final Long PERIOD_3_MONTHS = 3L;
	public static final Long PERIOD_6_MONTHS = 6L;
	public static final Long PERIOD_12_MONTHS = 12L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_plan")
	private Long id;

	@Column(name = "nm_plan", length = 100)
	private String name;

	@Column(name = "ds_plan", length = 500)
	private String description;

	@Column(name = "cd_plan", length = 20)
	private String code;

	@Column(name = "tp_plan", length = 2)
	private String type;

	@Column(name = "qt_period")
	private Long period;

	@Column(name = "qt_tests")
	private Long testQuantity;

	@Column(name = "vl_plan")
	private Double value;

	@Column(name = "fg_active")
	private Boolean active;

	public Plan() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getPeriod() {
		return period;
	}

	public void setPeriod(Long period) {
		this.period = period;
	}

	public Long getTestQuantity() {
		return testQuantity;
	}

	public void setTestQuantity(Long testQuantity) {
		this.testQuantity = testQuantity;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
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
