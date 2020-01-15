package br.com.bonaldoapps.trymee.entity.dto;

import br.com.bonaldoapps.trymee.entity.Answer;

public class AnswerDTO {

	private Long id;
	private QuestionDTO question;
	private String answerText;
	private AlternativeDTO answerAlternative;
	private ProcessDTO process;
	private CandidateDTO candidate;
	private Boolean correct;

	/**
	 * 
	 */
	public AnswerDTO(Answer model) {

		if (model != null) {

			if (model.getId() != null) {
				this.setId(model.getId());
			}

			if (model.getQuestion() != null) {
				this.setQuestion(new QuestionDTO(model.getQuestion()));
			}

			if (model.getAnswerText() != null) {
				this.setAnswerText(model.getAnswerText());
			}

			if (model.getAnswerAlternative() != null) {
				this.setAnswerAlternative(new AlternativeDTO(model.getAnswerAlternative()));
			}

			if (model.getProcess() != null) {
				this.setProcess(new ProcessDTO(model.getProcess()));
			}

			if (model.getCandidate() != null) {
				this.setCandidate(new CandidateDTO(model.getCandidate()));
			}

			if (model.getCorrect() != null) {
				this.setCorrect(model.getCorrect());
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
	 * @return the question
	 */
	public QuestionDTO getQuestion() {
		return question;
	}

	/**
	 * @param question
	 *            the question to set
	 */
	public void setQuestion(QuestionDTO question) {
		this.question = question;
	}

	/**
	 * @return the answerText
	 */
	public String getAnswerText() {
		return answerText;
	}

	/**
	 * @param answerText
	 *            the answerText to set
	 */
	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	/**
	 * @return the answerAlternative
	 */
	public AlternativeDTO getAnswerAlternative() {
		return answerAlternative;
	}

	/**
	 * @param answerAlternative
	 *            the answerAlternative to set
	 */
	public void setAnswerAlternative(AlternativeDTO answerAlternative) {
		this.answerAlternative = answerAlternative;
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
	 * @return the correct
	 */
	public Boolean getCorrect() {
		return correct;
	}

	/**
	 * @param correct
	 *            the correct to set
	 */
	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}

}
