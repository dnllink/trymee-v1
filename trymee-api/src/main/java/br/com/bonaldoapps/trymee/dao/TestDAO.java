/**
 * 
 */
package br.com.bonaldoapps.trymee.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.bonaldoapps.trymee.entity.Test;

/**
 * @author Daniel
 *
 */
@Stateless
public class TestDAO extends BaseDAO {

	@PersistenceContext
	EntityManager manager;

	/**
	 * @param activeParam
	 * @param level
	 * @param name
	 * @param description
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Test> listWithParams(Boolean activeParam, Long level, String name, String description, Long idUser) {

		Map<String, Object> parameters = new HashMap<String, Object>();

		StringBuilder strQuery = new StringBuilder("select t from Test t where 1 = 1 ");

		if (activeParam != null) {
			strQuery = strQuery.append(" and t.active = :activeParam ");
			parameters.put("activeParam", activeParam);
		}

		if (description != null && !description.isEmpty()) {
			strQuery = strQuery.append(" and t.description like :descriptionParam ");
			parameters.put("descriptionParam", "%" + description + "%");
		}

		if (level != null) {
			strQuery = strQuery.append(" and t.level = :levelParam ");
			parameters.put("levelParam", level);
		}

		if (name != null && !name.isEmpty()) {
			strQuery = strQuery.append(" and t.name like :nameParam ");
			parameters.put("nameParam", "%" + name + "%");
		}

		if (idUser != null) {
			strQuery = strQuery.append(" and t.user.id = :idUserParam ");
			parameters.put("idUserParam", idUser);
		}

		Query query = manager.createQuery(strQuery.toString(), Test.class);

		addQueryParameters(parameters, query);

		List<Test> tests = query.getResultList();

		return tests;

	}

	/**
	 * @param id
	 * @param eagerQuestions
	 * @param eagerCategories
	 * @param eagerGrades
	 * @return
	 */
	public Test find(Long id, Long idUser, Boolean eagerQuestions, Boolean eagerCategories) {

		StringBuilder strQuery = new StringBuilder(
				"select t from Test t where t.id = :idParam and t.user.id = :idUserParam ");

		Query query = manager.createQuery(strQuery.toString(), Test.class);

		query.setParameter("idParam", id);
		query.setParameter("idUserParam", idUser);

		Test test = null;

		try {

			test = (Test) query.getSingleResult();

		} catch (NoResultException nre) {
			return test;
		} catch (NonUniqueResultException nure) {
			return test;
		}

		if (test != null) {

			if (eagerQuestions) {
				test.getQuestions().size();
				test.setLoadedQuestions(true);
			}

			if (eagerCategories) {
				test.getCategories().size();
				test.setLoadedCategories(true);
			}

		}

		return test;
	}

	/**
	 * @param test
	 * @return
	 */
	public Test save(Test test) {

		manager.persist(test);

		return test;
	}

	/**
	 * @param test
	 */
	public Test update(Test test) {

		manager.merge(test);

		return test;

	}

	/**
	 * @param text
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Test> listByText(String text, Long idUser) {

		StringBuilder strQuery = new StringBuilder(
				"select distinct t from Test t join t.categories c where t.active = true and t.user.id = :idUserParam and (upper(t.name) like :textParam "
						+ "or upper(t.description) like :textParam " + "or upper(t.level.description) like :textParam "
						+ "or upper(c.description) like :textParam " + "or upper(c.code) like :textParam)");

		Query query = manager.createQuery(strQuery.toString(), Test.class);

		query.setParameter("textParam", "%" + text.toUpperCase() + "%");
		addUserParam(query, idUser);

		List<Test> tests = query.getResultList();

		return tests;

	}

}
