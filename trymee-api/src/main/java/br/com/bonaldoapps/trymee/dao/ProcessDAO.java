/**
 * 
 */
package br.com.bonaldoapps.trymee.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.bonaldoapps.trymee.entity.Process;

/**
 * @author Daniel
 *
 */
@Stateless
public class ProcessDAO extends BaseDAO {

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
	public List<Process> listWithParams(Boolean activeParam, Date startDate, Date finalDate, String description,
			Long idUser, Long startPagination, Long endPagination) {

		Map<String, Object> parameters = new HashMap<String, Object>();

		StringBuilder strQuery = new StringBuilder("select p from Process p where 1 = 1 ");

		if (activeParam != null) {
			strQuery = strQuery.append(" and p.active = :activeParam ");
			parameters.put("activeParam", activeParam);
		}

		if (description != null && !description.isEmpty()) {
			strQuery = strQuery.append(" and p.description like :descriptionParam ");
			parameters.put("descriptionParam", "%" + description + "%");
		}

		if (startDate != null) {
			strQuery = strQuery.append(" and p.startDate >= :startDateParam ");
			parameters.put("startDateParam", startDate);
		}

		if (finalDate != null) {
			strQuery = strQuery.append(" and p.startDate <= :finalDateParam ");
			parameters.put("finalDateParam", finalDate);
		}

		if (idUser != null) {
			strQuery = strQuery.append(" and p.user.id = :idUserParam ");
			parameters.put("idUserParam", idUser);
		}
		
		strQuery.append(" order by active desc, startDate desc, description asc ");

		Query query = manager.createQuery(strQuery.toString(), Process.class);
		
		if (startPagination != null && endPagination != null) {
			query.setFirstResult(startPagination.intValue() - 1);
			query.setMaxResults(endPagination.intValue());
		}

		addQueryParameters(parameters, query);

		return query.getResultList();

	}

	/**
	 * @param id
	 * @param eagerQuestions
	 * @param eagerCategories
	 * @param eagerGrades
	 * @return
	 */
	public Process find(Long id, Long idUser, Boolean eagerLinks, Boolean eagerCandidates) {

		StringBuilder strQuery = new StringBuilder(
				"select p from Process p where p.id = :idParam and p.user.id = :idUserParam ");

		Query query = manager.createQuery(strQuery.toString(), Process.class);

		query.setParameter("idParam", id);
		query.setParameter("idUserParam", idUser);

		Process process = null;

		try {

			process = (Process) query.getSingleResult();

		} catch (NoResultException nre) {

			return process;

		} catch (NonUniqueResultException nure) {

			return process;

		}

		if (process != null) {

			if (eagerLinks) {
				process.getLinks().size();
				process.setLoadedLinks(true);
			}

			if (eagerCandidates) {
				process.getCandidates().size();
				process.setLoadedCandidates(true);
			}

		}

		return process;
	}

	/**
	 * @param test
	 * @return
	 */
	public Process save(Process process) {

		manager.persist(process);

		return process;
	}

	/**
	 * @param test
	 */
	public Process update(Process process) {

		manager.merge(process);

		return process;

	}

}
