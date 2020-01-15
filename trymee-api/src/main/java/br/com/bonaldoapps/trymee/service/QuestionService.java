/**
 * 
 */
package br.com.bonaldoapps.trymee.service;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.bonaldoapps.trymee.dao.QuestionDAO;
import br.com.bonaldoapps.trymee.dao.UserDAO;
import br.com.bonaldoapps.trymee.entity.Alternative;
import br.com.bonaldoapps.trymee.entity.Question;
import br.com.bonaldoapps.trymee.entity.dto.QuestionDTO;
import br.com.bonaldoapps.trymee.entity.enums.QuestionTypeEnum;

/**
 * @author Daniel
 *
 */
@Stateless
public class QuestionService {

	@Inject
	private QuestionDAO questionDAO;

	@Inject
	private UserDAO userDAO;

	public QuestionDTO save(Question question, Long idUser) {

		question.setUser(userDAO.find(idUser));

		for (Alternative a : question.getAlternatives()) {
			a.setQuestion(question);
		}

		questionDAO.save(question);

		return new QuestionDTO(question);

	}

	/**
	 * @param activeParam
	 * @param typeId
	 * @param category
	 * @param level
	 * @return
	 */
	public List<QuestionDTO> listwithParams(Boolean active, QuestionTypeEnum type, Long category, Long level,
			Long idUser) {

		List<Question> questions = questionDAO.listwithParams(active, type, category, level, idUser);

		return convertToDTOList(questions);

	}

	public List<QuestionDTO> listByText(String text, Long idUser) {

		List<Question> questions = questionDAO.searchByText(text, idUser);

		return convertToDTOList(questions);

	}

	public QuestionDTO find(Long id, Boolean eagerTests, Long idUser) {

		Question entity = questionDAO.find(id, idUser, eagerTests);

		if (entity == null)
			return null;

		QuestionDTO dto = new QuestionDTO(entity);

		return dto;

	}

	/**
	 * @param id
	 */
	public void delete(Long id, Long idUser) {

		Question toDelete = questionDAO.find(id, idUser, false);

		if (toDelete != null)
			toDelete.setActive(false);

		questionDAO.update(toDelete);

	}

	/**
	 * @param questions
	 * @return
	 */
	private List<QuestionDTO> convertToDTOList(List<Question> questions) {

		List<QuestionDTO> dtos = new LinkedList<QuestionDTO>();

		for (Question question : questions) {
			dtos.add(new QuestionDTO(question));
		}

		return dtos;

	}

	/**
	 * @param id
	 */
	public void activate(long id, Long idUser) {

		Question toActivate = questionDAO.find(id, idUser, false);

		if (toActivate != null)
			toActivate.setActive(true);

		questionDAO.update(toActivate);

	}

	/**
	 * @param question
	 */
	public void update(Question question, Long idUser) {

		question.setUser(userDAO.find(idUser));

		for (Alternative a : question.getAlternatives()) {

			a.setQuestion(question);

		}

		questionDAO.update(question);

	}
}
