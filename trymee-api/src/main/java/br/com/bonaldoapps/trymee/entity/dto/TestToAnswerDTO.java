/**
 * 
 */
package br.com.bonaldoapps.trymee.entity.dto;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Daniel
 *
 */
public class TestToAnswerDTO {

	private String c;
	private List<QuestionToAnswerDTO> qs = new LinkedList<QuestionToAnswerDTO>();
	private String preOrientation;
	private String postOrientation;
	private String t;

	public TestToAnswerDTO() {

	}

	/**
	 * @return the preOrientation
	 */
	public String getPreOrientation() {
		return preOrientation;
	}

	/**
	 * @param preOrientation
	 *            the preOrientation to set
	 */
	public void setPreOrientation(String preOrientation) {
		this.preOrientation = preOrientation;
	}

	/**
	 * @return the postOrientation
	 */
	public String getPostOrientation() {
		return postOrientation;
	}

	/**
	 * @param postOrientation
	 *            the postOrientation to set
	 */
	public void setPostOrientation(String postOrientation) {
		this.postOrientation = postOrientation;
	}

	/**
	 * @return the c
	 */
	public String getC() {
		return c;
	}

	/**
	 * @param c
	 *            the c to set
	 */
	public void setC(String c) {
		this.c = c;
	}

	/**
	 * @return the qs
	 */
	public List<QuestionToAnswerDTO> getQs() {
		return qs;
	}

	/**
	 * @param qs
	 *            the qs to set
	 */
	public void setQs(List<QuestionToAnswerDTO> qs) {
		this.qs = qs;
	}

	/**
	 * @return the t
	 */
	public String getT() {
		return t;
	}

	/**
	 * @param t
	 *            the t to set
	 */
	public void setT(String t) {
		this.t = t;
	}

}
