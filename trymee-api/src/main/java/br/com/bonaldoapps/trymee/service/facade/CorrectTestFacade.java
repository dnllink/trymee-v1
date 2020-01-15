/**
 * 
 */
package br.com.bonaldoapps.trymee.service.facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.bonaldoapps.trymee.dao.AnswerDAO;
import br.com.bonaldoapps.trymee.dao.LinkDAO;
import br.com.bonaldoapps.trymee.dao.TestGradeDAO;
import br.com.bonaldoapps.trymee.entity.Answer;
import br.com.bonaldoapps.trymee.entity.Link;
import br.com.bonaldoapps.trymee.entity.TestGrade;
import br.com.bonaldoapps.trymee.entity.enums.QuestionTypeEnum;

/**
 * @author Daniel
 *
 */
@Stateless
public class CorrectTestFacade {

	@Inject
	private LinkDAO linkDAO;

	@Inject
	private AnswerDAO answerDAO;

	@Inject
	private TestGradeDAO testGradeDAO;

	/**
	 * @param test
	 */
	public void correctTest(String token) {

		Link link = linkDAO.findByToken(token);

		List<Answer> answers = answerDAO.listWithParameters(link.getCandidate().getId(), link.getProcess().getId(), null);

		Integer questionQuantity = answers.size();
		Integer correctQuantity = 0;
		Integer correctedQuantity = 0;

		for (Answer a : answers) {

			Boolean corrected = false;

			if (QuestionTypeEnum.MULTIPLA_ESCOLHA.equals(a.getQuestion().getType())) {

				a.setCorrect(a.getAnswerAlternative().isCorrect());
				answerDAO.update(a);
				corrected = true;

				if (a.getAnswerAlternative().isCorrect())
					correctQuantity++;

			} else {

				if (a.getCorrect() != null) {
					if (a.getCorrect()) {
						correctQuantity++;
					}
					corrected = true;
				}

			}

			if (corrected)
				correctedQuantity++;

		}

		Double rating = calculateRating(questionQuantity, correctQuantity);

		TestGrade grade = new TestGrade();

		grade.setCandidate(link.getCandidate());
		grade.setLink(link);
		grade.setProcess(link.getProcess());
		grade.setTest(link.getProcess().getTest());
		grade.setRating(rating);
		grade.setPartial(!(questionQuantity == correctedQuantity));

		testGradeDAO.save(grade);

	}

	public void updateGrade(Long idGrade) {

		TestGrade grade = testGradeDAO.find(idGrade);

		if (!grade.getPartial()) {
			// TODO lancar excecao de prova já corrigida completamente
		}

		List<Answer> answers = answerDAO.listWithParameters(grade.getCandidate().getId(), grade.getProcess().getId(), null);

		Integer questionQuantity = answers.size();
		Integer correctQuantity = 0;

		for (Answer a : answers) {

			if (a.getCorrect()) {
				correctQuantity++;
			}

		}

		Double rating = calculateRating(questionQuantity, correctQuantity);

		grade.setRating(rating);
		grade.setPartial(false);

		testGradeDAO.update(grade);

	}

	private Double calculateRating(Integer questions, Integer correct) {

		Double value = 100d / questions;
		Double grade = value * correct;

		return grade;

	}

}
