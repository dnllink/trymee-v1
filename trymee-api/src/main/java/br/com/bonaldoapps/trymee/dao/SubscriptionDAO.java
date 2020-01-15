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
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.bonaldoapps.trymee.entity.Subscription;

/**
 * @author Daniel
 *
 */
@Stateless
public class SubscriptionDAO extends BaseDAO {

	@PersistenceContext
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	public List<Subscription> listSubscriptions(Long idUser, Date dateParam, Boolean activeParam) {

		Map<String, Object> parameters = new HashMap<String, Object>();

		StringBuilder strQuery = new StringBuilder("select s from Subscription s where 1 = 1 ");

		if (idUser != null) {
			strQuery.append(" and s.user.id = :idUserParam ");
			parameters.put("idUserParam", idUser);
		}

		if (dateParam != null) {
			strQuery.append(" and (s.dateSubscription <= :dateParam ");
			strQuery.append(" and s.dateEndPeriod >= :dateParam) ");
			parameters.put("dateParam", dateParam);
		}

		if (activeParam != null) {
			strQuery.append(" and s.active = :activeParam ");
			parameters.put("activeParam", activeParam);
		}

		Query query = manager.createQuery(strQuery.toString(), Subscription.class);

		addQueryParameters(parameters, query);

		List<Subscription> list = query.getResultList();

		return list;

	}

	public Subscription save(Subscription sub) {

		manager.persist(sub);

		return sub;

	}

	public Subscription update(Subscription sub) {

		manager.merge(sub);

		return sub;

	}

	public void detachEntity(Subscription sub) {

		manager.detach(sub);

	}

}