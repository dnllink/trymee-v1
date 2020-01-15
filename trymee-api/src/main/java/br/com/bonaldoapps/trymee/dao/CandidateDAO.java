/**
 * 
 */
package br.com.bonaldoapps.trymee.dao;

import java.math.BigInteger;
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

import br.com.bonaldoapps.trymee.entity.Candidate;

/**
 * @author Daniel
 *
 */
@Stateless
public class CandidateDAO extends BaseDAO {

	@PersistenceContext
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	public List<Candidate> list(String name, String email, Boolean active, Long idUser) {

		Map<String, Object> parameters = new HashMap<String, Object>();

		String strQuery = "select c from Candidate c where 1 = 1 ";

		if (active != null) {
			strQuery = strQuery.concat(" and c.active = :activeParam ");
			parameters.put("activeParam", active);
		}

		if (name != null && !name.isEmpty()) {
			strQuery = strQuery.concat(" and c.name like :nameParam ");
			parameters.put("nameParam", "%" + name + "%");
		}

		if (email != null && !email.isEmpty()) {
			strQuery = strQuery.concat(" and c.email like :emailParam ");
			parameters.put("emailParam", "%" + email + "%");
		}

		if (idUser != null) {
			strQuery = strQuery.concat(" and c.user.id = :idUserParam ");
			parameters.put("idUserParam", idUser);
		}

		Query query = manager.createQuery(strQuery, Candidate.class);

		addQueryParameters(parameters, query);

		return query.getResultList();

	}

	public Candidate find(Long id, Long idUser, Boolean eagerProcesses, Boolean eagerGrades, Boolean eagerLinks) {

		String strQuery = "select c from Candidate c where c.id = :idParam and c.user.id = :idUserParam ";

		Query query = manager.createQuery(strQuery, Candidate.class);

		query.setParameter("idParam", id);
		query.setParameter("idUserParam", idUser);

		Candidate candidate = null;

		try {

			candidate = (Candidate) query.getSingleResult();

		} catch (NoResultException nre) {

			return candidate;

		} catch (NonUniqueResultException nure) {

			return candidate;

		}

		if (candidate != null) {

			if (eagerProcesses) {
				candidate.getProcesses().size();
				candidate.setLoadedProcesses(true);
			}

			if (eagerGrades) {
				candidate.getTestGrades().size();
				candidate.setLoadedGrades(true);
			}

			if (eagerLinks) {
				candidate.getLinks().size();
				candidate.setLoadedLinks(true);
			}

		}

		return candidate;

	}

	public Candidate save(Candidate candidate) {

		manager.persist(candidate);

		return candidate;

	}

	public Candidate update(Candidate candidate) {

		manager.merge(candidate);

		return candidate;

	}

	/**
	 * @param text
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Candidate> listByText(String text, Long idUser) {

		String strQuery = "select c from Candidate c where c.active = true and c.user.id = :idUserParam and (c.name like :textParam or c.email like :textParam)";

		Query query = manager.createQuery(strQuery, Candidate.class);
		query.setParameter("textParam", "%" + text + "%");
		query.setParameter("idUserParam", idUser);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Candidate> listByProcessWithLinks(Long idProcess, Long idUser) {

		StringBuilder sb = new StringBuilder(" SELECT ");

		sb.append("     c.id_candidate, c.nm_candidate, c.ds_email, l.fg_active, l.ds_url ");
		sb.append(" FROM ");
		sb.append("     candidates c ");
		sb.append("         JOIN ");
		sb.append("     process_candidates pc ON c.id_candidate = pc.id_candidate ");
		sb.append("         LEFT JOIN ");
		sb.append("     links l ON pc.id_process = l.id_process ");
		sb.append("         AND c.id_candidate = l.id_candidate ");
		sb.append(" WHERE ");
		sb.append("     c.id_user = :idUserParam AND pc.id_process = :idProcessParam ");

		Query query = manager.createNativeQuery(sb.toString());
		query.setParameter("idProcessParam", idProcess);
		query.setParameter("idUserParam", idUser);

		List<Object> lines = query.getResultList();

		List<Candidate> candidates = new LinkedList<Candidate>();

		for (Object line : lines) {
			Object[] obj = (Object[]) line;

			BigInteger id = (BigInteger) obj[0];
			String name = (String) obj[1];
			String email = (String) obj[2];
			Boolean active = (Boolean) obj[3];
			String linkUrl = (String) obj[4];

			candidates.add(new Candidate(id.longValue(), name, email, active, linkUrl));

		}

		return candidates;
	}

}
