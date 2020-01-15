/**
 * 
 */
package br.com.bonaldoapps.trymee.dao;

import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 * @author Daniel
 *
 */
@Stateless
public class BaseDAO {

	protected void addQueryParameters(Map<String, Object> parameters, Query query) {

		if (parameters != null && !parameters.isEmpty() && query != null) {

			for (Map.Entry<String, Object> entry : parameters.entrySet()) {

				query.setParameter(entry.getKey(), entry.getValue());

			}

		}

	}

	public void addUserParam(Query query, Long idUser) {

		query.setParameter("idUserParam", idUser);

	}

}
