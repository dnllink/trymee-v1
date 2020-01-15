/**
 * 
 */
package br.com.bonaldoapps.trymee.entity.dto;

import java.util.LinkedList;
import java.util.List;

import br.com.bonaldoapps.trymee.entity.Alternative;
import br.com.bonaldoapps.trymee.entity.Question;

/**
 * @author Daniel
 *
 */
public class QuestionToAnswerDTO {

	private Long i;
	private Long n;
	private String d;
	private Long t;
	private String a;
	private String im;
	private Long c;
	private List<AlternativeToAnswerDTO> as = new LinkedList<AlternativeToAnswerDTO>();

	public QuestionToAnswerDTO() {

	}

	public QuestionToAnswerDTO(Question model, Long number) {

		this.i = model.getId();
		this.n = number;
		this.d = model.getDescription();
		this.t = model.getType().getId();
		this.a = new String();
		this.im = model.getImageLink();

		if (model.getAlternatives() != null) {

			for (Alternative a : model.getAlternatives()) {

				as.add(new AlternativeToAnswerDTO(a));

			}

		}

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
	 * @return the n
	 */
	public Long getN() {
		return n;
	}

	/**
	 * @param n
	 *            the n to set
	 */
	public void setN(Long n) {
		this.n = n;
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

	/**
	 * @return the t
	 */
	public Long getT() {
		return t;
	}

	/**
	 * @param t
	 *            the t to set
	 */
	public void setT(Long t) {
		this.t = t;
	}

	/**
	 * @return the a
	 */
	public String getA() {
		return a;
	}

	/**
	 * @param a
	 *            the a to set
	 */
	public void setA(String a) {
		this.a = a;
	}

	/**
	 * @return the im
	 */
	public String getIm() {
		return im;
	}

	/**
	 * @param im
	 *            the im to set
	 */
	public void setIm(String im) {
		this.im = im;
	}

	/**
	 * @return the as
	 */
	public List<AlternativeToAnswerDTO> getAs() {
		return as;
	}

	/**
	 * @param as
	 *            the as to set
	 */
	public void setAs(List<AlternativeToAnswerDTO> as) {
		this.as = as;
	}

	/**
	 * @return the c
	 */
	public Long getC() {
		return c;
	}

	/**
	 * @param c
	 *            the c to set
	 */
	public void setC(Long c) {
		this.c = c;
	}

}
