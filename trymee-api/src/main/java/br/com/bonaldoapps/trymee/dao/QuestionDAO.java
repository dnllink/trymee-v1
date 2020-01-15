/**
 * 
 */
package br.com.bonaldoapps.trymee.dao;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.bonaldoapps.trymee.entity.Question;
import br.com.bonaldoapps.trymee.entity.enums.QuestionTypeEnum;

/**
 * @author Daniel
 *
 */
@Stateless
public class QuestionDAO extends BaseDAO {

	@PersistenceContext
	EntityManager manager;

	public Question save(Question question) {
		manager.persist(question);
		return question;
	}

	public Question update(Question question) {
		manager.merge(question);
		return question;
	}

	public Question find(Long id, Long idUser, boolean eagerTests) {

		if (id == null)
			return null;

		String strQuery = "select q from Question q where q.user.id = :idUserParam and q.id = :idParam";

		Query query = manager.createQuery(strQuery, Question.class);

		query.setParameter("idUserParam", idUser);
		query.setParameter("idParam", id);

		Question q = null;

		try {

			q = (Question) query.getSingleResult();

		} catch (NoResultException nre) {
			return q;
		} catch (NonUniqueResultException nure) {
			return q;
		}

		if (q != null && eagerTests) {
			q.getTests().size();
			q.setLoadedTests(true);
		}

		return q;
	}

	/**
	 * @param active
	 * @param type
	 * @param category
	 * @param level
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Question> listwithParams(Boolean active, QuestionTypeEnum type, Long category, Long level,
			Long idUser) {

		Map<String, Object> parameters = new HashMap<String, Object>();

		String strQuery = "select q from Question q where q.user.id = :idUserParam ";

		if (active != null) {
			strQuery = strQuery.concat(" and q.active = :activeParam ");
			parameters.put("activeParam", active);
		}

		if (type != null) {
			strQuery = strQuery.concat(" and q.type = :typeParam ");
			parameters.put("typeParam", type);
		}

		if (category != null && category != 0) {
			strQuery = strQuery.concat(" and q.category = :categoryParam ");
			parameters.put("categoryParam", category);
		}

		if (level != null && level != 0) {
			strQuery = strQuery.concat(" and q.level = :levelParam ");
			parameters.put("levelParam", level);
		}

		if (idUser != null) {
			strQuery = strQuery.concat(" and q.user.id = :idUserParam ");
			parameters.put("idUserParam", idUser);
		}

		Query query = manager.createQuery(strQuery, Question.class);

		addQueryParameters(parameters, query);

		return query.getResultList();

	}

	/**
	 * @param active
	 * @param type
	 * @param category
	 * @param level
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Question> searchByText(String text, Long idUser) {

		List<Question> questions = new LinkedList<Question>();

		if (text != null && !text.isEmpty()) {

			String strQuery = "select q from Question q where q.active = true and q.user.id = :idUserParam and (q.description like :textParam or q.category.description like :textParam or q.level.description like :textParam)";

			Query query = manager.createQuery(strQuery, Question.class);

			addUserParam(query, idUser);

			query.setParameter("textParam", "%" + text + "%");

			questions = query.getResultList();

		}

		return questions;

	}

}
