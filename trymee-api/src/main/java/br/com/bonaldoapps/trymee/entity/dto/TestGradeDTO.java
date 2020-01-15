package br.com.bonaldoapps.trymee.entity.dto;

import br.com.bonaldoapps.trymee.entity.TestGrade;

public class TestGradeDTO {

	private Long id;

	private CandidateDTO candidate;

	private TestDTO test;

	private ProcessDTO process;

	private LinkDTO link;

	private Double rating;

	private Boolean partial;

	/**
	 * 
	 */
	public TestGradeDTO() {
	}

	/**
	 * @param g
	 */
	public TestGradeDTO(TestGrade model) {

		if (model != null) {

			if (model.getId() != null) {
				this.setId(model.getId());
			}

			if (model.getCandidate() != null) {
				this.setCandidate(new CandidateDTO(model.getCandidate()));
			}

			if (model.getTest() != null) {
				this.setTest(new TestDTO(model.getTest()));
			}

			if (model.getProcess() != null) {
				this.setProcess(new ProcessDTO(model.getProcess()));
			}

			if (model.getLink() != null) {
				this.setLink(new LinkDTO(model.getLink(), false));
			}

			if (model.getRating() != null) {
				this.setRating(model.getRating());
			}

			if (model.getPartial() != null) {
				this.setPartial(model.getPartial());
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
	 * @return the candidate
	 */
	public CandidateDTO getCandidate() {
		return candidate;
	}

	/**
	 * @param candidate
	 *            the candidate to set
	 */
	public void setCandidate(CandidateDTO candidate) {
		this.candidate = candidate;
	}

	/**
	 * @return the test
	 */
	public TestDTO getTest() {
		return test;
	}

	/**
	 * @param test
	 *            the test to set
	 */
	public void setTest(TestDTO test) {
		this.test = test;
	}

	/**
	 * @return the link
	 */
	public LinkDTO getLink() {
		return link;
	}

	/**
	 * @param link
	 *            the link to set
	 */
	public void setLink(LinkDTO link) {
		this.link = link;
	}

	/**
	 * @return the rating
	 */
	public Double getRating() {
		return rating;
	}

	/**
	 * @param rating
	 *            the rating to set
	 */
	public void setRating(Double rating) {
		this.rating = rating;
	}

	/**
	 * @return the process
	 */
	public ProcessDTO getProcess() {
		return process;
	}

	/**
	 * @param process
	 *            the process to set
	 */
	public void setProcess(ProcessDTO process) {
		this.process = process;
	}

	/**
	 * @return the partial
	 */
	public Boolean getPartial() {
		return partial;
	}

	/**
	 * @param partial
	 *            the partial to set
	 */
	public void setPartial(Boolean partial) {
		this.partial = partial;
	}

}
