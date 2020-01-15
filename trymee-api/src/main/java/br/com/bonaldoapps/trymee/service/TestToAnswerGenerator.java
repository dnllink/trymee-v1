/**
 * 
 */
package br.com.bonaldoapps.trymee.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.bonaldoapps.trymee.dao.LinkDAO;
import br.com.bonaldoapps.trymee.dao.TestDAO;
import br.com.bonaldoapps.trymee.entity.Link;
import br.com.bonaldoapps.trymee.entity.Question;
import br.com.bonaldoapps.trymee.entity.Test;
import br.com.bonaldoapps.trymee.entity.dto.QuestionToAnswerDTO;
import br.com.bonaldoapps.trymee.entity.dto.TestToAnswerDTO;
import br.com.bonaldoapps.trymee.exception.InvalidLinkException;

/**
 * @author Daniel
 *
 */
@Stateless
public class TestToAnswerGenerator {

	@Inject
	private TestDAO testDAO;

	@Inject
	private LinkDAO linkDAO;

	/**
	 * @param token
	 * @return
	 * @throws InvalidLinkException
	 */
	public TestToAnswerDTO getTest(String token) throws InvalidLinkException {

		TestToAnswerDTO testToAnswer = new TestToAnswerDTO();

		Link link = linkDAO.findByToken(token);

		if (link == null)
			throw new InvalidLinkException("Link para prova inválido.");

		if (!link.getActive())
			throw new InvalidLinkException("Este link já foi utilizado e não é mais válido.");

		Test test = testDAO.find(link.getProcess().getTest().getId(), link.getUser().getId(), true, false);

		convertToDTOList(test, testToAnswer);

		testToAnswer.setC(link.getCandidate().getName());

		return testToAnswer;
	}

	/**
	 * @param test
	 */
	private void convertToDTOList(Test test, TestToAnswerDTO testToAnswer) {

		Long number = 1l;

		for (Question q : test.getQuestions()) {

			testToAnswer.getQs().add(new QuestionToAnswerDTO(q, number));
			number++;

		}

	}

}
