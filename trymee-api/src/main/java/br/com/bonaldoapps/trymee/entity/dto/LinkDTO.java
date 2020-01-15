package br.com.bonaldoapps.trymee.entity.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.bonaldoapps.trymee.entity.Link;

public class LinkDTO {

	private Long id;

	private String code;

	private String description;

	private String url;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm", locale = "pt-BR")
	private Date dtFirstSent;

	private ProcessDTO process;

	private CandidateDTO candidate;

	/**
	 * 
	 */
	public LinkDTO() {

	}

	/**
	 * @param l
	 */
	public LinkDTO(Link model, Boolean loadProcess) {

		if (model == null)
			return;

		if (model.getId() != null) {
			setId(model.getId());
		}

		if (model.getCode() != null) {
			setCode(model.getCode());
		}

		if (model.getDescription() != null) {
			setDescription(model.getDescription());
		}

		if (model.getUrl() != null) {
			setUrl(model.getUrl());
		}

		if (loadProcess && model.getProcess() != null) {
			setProcess(new ProcessDTO(model.getProcess()));
		}

		if (model.getCandidate() != null) {
			setCandidate(new CandidateDTO(model.getCandidate()));
		}

		if (model.getDtFirstSent() != null) {
			setDtFirstSent(model.getDtFirstSent());
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
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
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
	 * @return the dtFirstSent
	 */
	public Date getDtFirstSent() {
		return dtFirstSent;
	}

	/**
	 * @param dtFirstSent
	 *            the dtFirstSent to set
	 */
	public void setDtFirstSent(Date dtFirstSent) {
		this.dtFirstSent = dtFirstSent;
	}

}
