/**
 * 
 */
package br.com.bonaldoapps.trymee.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.bonaldoapps.trymee.dao.LevelDAO;
import br.com.bonaldoapps.trymee.dao.ProcessDAO;
import br.com.bonaldoapps.trymee.dao.QuestionDAO;
import br.com.bonaldoapps.trymee.dao.TestDAO;
import br.com.bonaldoapps.trymee.dao.UserDAO;
import br.com.bonaldoapps.trymee.entity.Category;
import br.com.bonaldoapps.trymee.entity.Process;
import br.com.bonaldoapps.trymee.entity.Question;
import br.com.bonaldoapps.trymee.entity.Test;
import br.com.bonaldoapps.trymee.entity.dto.TestDTO;

/**
 * @author Daniel
 *
 */
@Stateless
public class TestService {

	@Inject
	private TestDAO testDAO;

	@Inject
	private QuestionDAO questionDAO;

	@Inject
	private LevelDAO levelDAO;

	@Inject
	private UserDAO userDAO;

	@Inject
	private ProcessDAO processDAO;

	/**
	 * @param activeParam
	 * @param level
	 * @param name
	 * @param description
	 * @return
	 */
	public List<TestDTO> listWithParams(Boolean activeParam, Long level, String name, String description, Long idUser) {

		List<Test> tests = testDAO.listWithParams(activeParam, level, name, description, idUser);

		return convertToDTOList(tests);

	}

	public List<TestDTO> listByText(String text, Long idUser) {

		List<Test> tests = testDAO.listByText(text, idUser);

		return convertToDTOList(tests);

	}

	/**
	 * @param tests
	 * @return
	 */
	private List<TestDTO> convertToDTOList(List<Test> tests) {

		List<TestDTO> dtos = new LinkedList<TestDTO>();

		for (Test test : tests) {
			dtos.add(new TestDTO(test));
		}

		return dtos;

	}

	/**
	 * @param id
	 * @param b
	 * @param c
	 * @param d
	 * @return
	 */
	public TestDTO find(Long id, Boolean eagerQuestions, Boolean eagerCategories, Long idUser) {

		Test entity = testDAO.find(id, idUser, eagerQuestions, eagerCategories);

		return new TestDTO(entity);
	}

	/**
	 * @param test
	 * @return
	 */
	public TestDTO save(Test test, Long idUser) {

		List<Question> questions = test.getQuestions();
		test.setQuestions(new ArrayList<Question>());
		Map<Long, Category> categories = new HashMap<Long, Category>();

		for (Question q : questions) {

			if (q.getId() != null) {
				q = questionDAO.find(q.getId(), idUser, true);
			} else {
				questionDAO.save(q);
			}

			test.addQuestion(q);
			categories.put(q.getCategory().getId(), q.getCategory());

		}

		test.setCategories(new ArrayList<Category>());

		for (Map.Entry<Long, Category> entry : categories.entrySet()) {

			test.addCategory(entry.getValue());

		}

		test.setUser(userDAO.find(idUser));

		Test savedTest = testDAO.save(test);

		if (test.getIdProcess() != null) {
			Process process = processDAO.find(test.getIdProcess(), idUser, false, false);
			if (process != null) {
				process.setTest(savedTest);
				processDAO.update(process);
			}
		}

		return new TestDTO(savedTest);

	}

	/**
	 * @param id
	 */
	public void delete(Long id, Long idUser) {

		Test toDelete = testDAO.find(id, idUser, false, false);

		if (toDelete != null)
			toDelete.setActive(false);

		testDAO.update(toDelete);

	}

	/**
	 * @param id
	 */
	public void activate(Long id, Long idUser) {

		Test toUpdate = testDAO.find(id, idUser, false, false);

		if (toUpdate != null)
			toUpdate.setActive(true);

		testDAO.update(toUpdate);

	}

	/**
	 * @param test
	 */
	public TestDTO update(Test test, Long idUser) {

		if (test == null)
			return null;

		if (test.getId() == null)
			return null;

		Test toUpdate = testDAO.find(test.getId(), idUser, true, true);

		if (toUpdate == null)
			return null;

		toUpdate.setActive(test.isActive());
		toUpdate.setDescription(test.getDescription());
		toUpdate.setLevel(levelDAO.find(test.getLevel().getId()));
		toUpdate.setName(test.getName());

		toUpdate.getCategories().clear();
		toUpdate.getQuestions().clear();

		testDAO.update(toUpdate);

		Map<Long, Category> categories = new HashMap<Long, Category>();
		List<Long> ids = new LinkedList<Long>();

		for (Question q : test.getQuestions()) {

			if (q.getId() == null) {
				questionDAO.save(q);
			}

			ids.add(q.getId());
			categories.put(q.getCategory().getId(), q.getCategory());

		}

		for (Map.Entry<Long, Category> entry : categories.entrySet()) {

			toUpdate.addCategory(entry.getValue());

		}

		for (Long id : ids) {

			Question q = questionDAO.find(id, idUser, true);
			toUpdate.addQuestion(q);
			q.setLoadedTests(false);

		}

		test.setUser(userDAO.find(idUser));
		testDAO.update(toUpdate);

		return new TestDTO(toUpdate);

	}

}
