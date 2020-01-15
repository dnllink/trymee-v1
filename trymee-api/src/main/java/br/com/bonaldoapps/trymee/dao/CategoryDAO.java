/**
 * 
 */
package br.com.bonaldoapps.trymee.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.bonaldoapps.trymee.entity.Category;

/**
 * @author Daniel
 *
 */
@Stateless
public class CategoryDAO {

	@PersistenceContext
	private EntityManager manager;

	public List<Category> listAll() {

		return manager.createQuery("select c from Category c order by c.description asc", Category.class)
				.getResultList();

	}

	public Category find(Long id) {

		return manager.find(Category.class, id);

	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Category> searchByText(String text) {

		Query query = manager.createQuery(
				"select c from Category c where c.description like :textParam or c.code like :textParam order by c.description asc",
				Category.class);

		query.setParameter("textParam", "%" + text + "%");

		return query.getResultList();

	}

	/**
	 * @return
	 */
	public Category searchByCode(String text) {

		Query query = manager.createQuery("select c from Category c where c.code = :textParam", Category.class);

		query.setParameter("textParam", text);

		return (Category) query.getSingleResult();

	}

}
