/**
 * 
 */
package br.com.bonaldoapps.trymee.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bonaldoapps.trymee.entity.Alternative;

/**
 * @author Daniel
 *
 */
@Stateless
public class AlternativeDAO {

	@PersistenceContext
	EntityManager manager;

	public Alternative save(Alternative a) {
		manager.persist(a);
		return a;
	}

	public Alternative update(Alternative a) {
		manager.merge(a);
		return a;
	}

	public Alternative find(Long id) {

		Alternative a = manager.find(Alternative.class, id);

		return a;
	}

}
