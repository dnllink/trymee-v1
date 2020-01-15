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
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.bonaldoapps.trymee.entity.Link;

/**
 * @author Daniel
 *
 */
@Stateless
public class LinkDAO extends BaseDAO {

	@PersistenceContext
	EntityManager manager;

	public Link findByToken(String token) {

		Query query = manager.createQuery("select l from Link l where l.code = :tokenParam", Link.class);

		query.setParameter("tokenParam", token);

		Link link;

		try {

			link = (Link) query.getSingleResult();

		} catch (NoResultException nre) {

			link = null;

		}

		return link;
	}

	@SuppressWarnings("unchecked")
	public List<Link> listWithParams(Long idProcess, Long idCandidate, Long idUser) {

		Map<String, Object> parameters = new HashMap<String, Object>();

		StringBuilder sb = new StringBuilder("select l from Link l where l.process.id = :idProcessParam ");

		if (idCandidate != null) {

			sb.append(" and l.candidate.id = :idCandidateParam ");
			parameters.put("idCandidateParam", idCandidate);

		}

		if (idUser != null) {

			sb.append(" and l.user.id = :idUserParam ");
			parameters.put("idUserParam", idUser);

		}

		Query query = manager.createQuery(sb.toString(), Link.class);
		query.setParameter("idProcessParam", idProcess);

		addQueryParameters(parameters, query);

		return query.getResultList();

	}

	public void save(Link link) {

		manager.persist(link);

	}

	public void update(Link link) {

		manager.merge(link);

	}

}
