/**
 * 
 */
package br.com.bonaldoapps.trymee.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.bonaldoapps.trymee.entity.Plan;

/**
 * @author Daniel
 *
 */
@Stateless
public class PlanDAO {

	@PersistenceContext
	EntityManager manager;

	public Plan save(Plan plan) {
		manager.persist(plan);
		return plan;
	}

	public Plan update(Plan plan) {
		manager.merge(plan);
		return plan;
	}

	public Plan find(Long id) {
		Plan p = manager.find(Plan.class, id);
		return p;
	}

	public Plan findFreePlan() {

		Plan p;

		StringBuilder sb = new StringBuilder("select p from Plan p where p.code = :codeParam");

		Query query = manager.createQuery(sb.toString(), Plan.class);

		query.setParameter("codeParam", Plan.FREE_PLAN);

		try {

			p = (Plan) query.getSingleResult();

		} catch (NoResultException nre) {

			p = null;

		}

		return p;

	}

}
