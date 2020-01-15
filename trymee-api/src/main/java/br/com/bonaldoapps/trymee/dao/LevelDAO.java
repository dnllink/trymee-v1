/**
 * 
 */
package br.com.bonaldoapps.trymee.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bonaldoapps.trymee.entity.Level;

/**
 * @author Daniel
 *
 */
@Stateless
public class LevelDAO {

	@PersistenceContext
	private EntityManager manager;

	public List<Level> listAll() {

		return manager.createQuery("select l from Level l", Level.class).getResultList();

	}

	public Level find(Long id) {

		return manager.find(Level.class, id);

	}

}
