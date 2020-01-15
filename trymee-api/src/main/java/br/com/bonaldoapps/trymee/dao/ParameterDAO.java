/**
 * 
 */
package br.com.bonaldoapps.trymee.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.bonaldoapps.trymee.entity.Parameter;

/**
 * @author Daniel
 *
 */
@Stateless
public class ParameterDAO extends BaseDAO {

	@PersistenceContext
	private EntityManager manager;

	/**
	 * @param template
	 * @return
	 */
	public String findParameter(String template) {

		Query query = manager.createQuery("select p from Parameter p where p.code = :codeParam", Parameter.class);

		query.setParameter("codeParam", template);

		Parameter p = (Parameter) query.getSingleResult();

		return p.getContent();
	}

}
