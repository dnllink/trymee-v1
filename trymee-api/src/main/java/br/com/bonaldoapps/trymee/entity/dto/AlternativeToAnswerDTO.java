/**
 * 
 */
package br.com.bonaldoapps.trymee.entity.dto;

import br.com.bonaldoapps.trymee.entity.Alternative;

/**
 * @author Daniel
 *
 */
public class AlternativeToAnswerDTO {

	private Long i;
	private String d;

	public AlternativeToAnswerDTO() {
	}

	public AlternativeToAnswerDTO(Alternative model) {

		this.i = model.getId();
		this.d = model.getDescription();

	}

	/**
	 * @return the i
	 */
	public Long getI() {
		return i;
	}

	/**
	 * @param i
	 *            the i to set
	 */
	public void setI(Long i) {
		this.i = i;
	}

	/**
	 * @return the d
	 */
	public String getD() {
		return d;
	}

	/**
	 * @param d
	 *            the d to set
	 */
	public void setD(String d) {
		this.d = d;
	}

}