package br.com.bonaldoapps.trymee.entity.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.bonaldoapps.trymee.entity.Candidate;
import br.com.bonaldoapps.trymee.entity.Link;
import br.com.bonaldoapps.trymee.entity.Process;
import br.com.bonaldoapps.trymee.entity.TestGrade;

public class CandidateDTO {

	private Long id;

	private String name;

	private String email;

	private Boolean active;

	private List<ProcessDTO> processes;

	private List<TestGradeDTO> testGrades;

	private List<LinkDTO> links;

	private Boolean link;
	
	private String linkUrl;

	/**
	 * 
	 */
	public CandidateDTO() {

	}

	/**
	 * @param candidate
	 */
	public CandidateDTO(Candidate model) {

		if (model == null) {
			return;
		}

		if (model.getId() != null) {
			this.setId(model.getId());
		}

		if (model.getName() != null) {
			this.setName(model.getName());
		}

		if (model.getEmail() != null) {
			this.setEmail(model.getEmail());
		}

		if (model.getActive() != null) {
			this.setActive(model.getActive());
		}

		if (model.getLink() != null) {
			this.link = model.getLink();
		}
		
		if (model.getLinkUrl() != null) {
			this.linkUrl = model.getLinkUrl();
		}

		if (model.getLinks() != null && model.getLoadedLinks()) {

			this.setLinks(new ArrayList<LinkDTO>());

			for (Link l : model.getLinks()) {

				this.getLinks().add(new LinkDTO(l, false));

			}

		}

		if (model.getTestGrades() != null && model.getLoadedGrades()) {

			this.setTestGrades(new ArrayList<TestGradeDTO>());

			for (TestGrade t : model.getTestGrades()) {

				this.getTestGrades().add(new TestGradeDTO(t));

			}

		}

		if (model.getProcesses() != null && model.getLoadedProcesses()) {

			this.setProcesses(new ArrayList<ProcessDTO>());

			for (Process p : model.getProcesses()) {

				this.getProcesses().add(new ProcessDTO(p));

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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * @return the processes
	 */
	public List<ProcessDTO> getProcesses() {
		return processes;
	}

	/**
	 * @param processes
	 *            the processes to set
	 */
	public void setProcesses(List<ProcessDTO> processes) {
		this.processes = processes;
	}

	/**
	 * @return the testGrades
	 */
	public List<TestGradeDTO> getTestGrades() {
		return testGrades;
	}

	/**
	 * @param testGrades
	 *            the testGrades to set
	 */
	public void setTestGrades(List<TestGradeDTO> testGrades) {
		this.testGrades = testGrades;
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
	 * @return the link
	 */
	public Boolean getLink() {
		return link;
	}

	/**
	 * @param link
	 *            the link to set
	 */
	public void setLink(Boolean link) {
		this.link = link;
	}

	/**
	 * @return the linkUrl
	 */
	public String getLinkUrl() {
		return linkUrl;
	}

	/**
	 * @param linkUrl the linkUrl to set
	 */
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

}
