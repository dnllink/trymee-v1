package br.com.bonaldoapps.trymee.entity.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.bonaldoapps.trymee.entity.Candidate;
import br.com.bonaldoapps.trymee.entity.Link;
import br.com.bonaldoapps.trymee.entity.Process;

public class ProcessDTO {

	private Long id;

	private String description;

	// @JsonFormat(pattern = "dd/MM/yyyy", locale = "GMT-2")
	private Date startDate;

	// @JsonFormat(pattern = "dd/MM/yyyy", locale = "GMT-2")
	private Date endDate;

	private Boolean active = true;

	private TestDTO test;

	private List<CandidateDTO> candidates;

	private List<LinkDTO> links;

	/**
	 * 
	 */
	public ProcessDTO() {
	}

	/**
	 * @param p
	 */
	public ProcessDTO(Process model) {

		if (model == null)
			return;

		if (model.getId() != null) {
			setId(model.getId());
		}

		if (model.getDescription() != null) {
			setDescription(model.getDescription());
		}

		if (model.getStartDate() != null) {
			setStartDate(model.getStartDate());
		}

		if (model.getEndDate() != null) {
			setEndDate(model.getEndDate());
		}

		if (model.getActive() != null) {
			setActive(model.getActive());
		}

		if (model.getTest() != null) {
			setTest(new TestDTO(model.getTest()));
		}

		if (model.getLoadedLinks() && model.getLinks().size() > 0) {

			setLinks(new ArrayList<LinkDTO>());

			for (Link l : model.getLinks()) {

				getLinks().add(new LinkDTO(l, false));

			}

		}

		if (model.getLoadedCandidates() && model.getCandidates().size() > 0) {

			setCandidates(new ArrayList<CandidateDTO>());

			for (Candidate c : model.getCandidates()) {

				getCandidates().add(new CandidateDTO(c));

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
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
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
	 * @return the candidates
	 */
	public List<CandidateDTO> getCandidates() {
		return candidates;
	}

	/**
	 * @param candidates
	 *            the candidates to set
	 */
	public void setCandidates(List<CandidateDTO> candidates) {
		this.candidates = candidates;
	}

	/**
	 * @return the links
	 */
	public List<LinkDTO> getLinks() {
		return links;
	}

	/**
	 * @param links
	 *            the links to set
	 */
	public void setLinks(List<LinkDTO> links) {
		this.links = links;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
