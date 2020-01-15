/**
 * 
 */
package br.com.bonaldoapps.trymee.dao;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.bonaldoapps.trymee.entity.User;

/**
 * @author Daniel
 *
 */
@Stateless
public class UserDAO {

	@PersistenceContext
	EntityManager manager;

	public User save(User user) {
		manager.persist(user);
		return user;
	}

	public User update(User user) {
		manager.merge(user);
		return user;
	}

	public User find(Long id) {
		User u = manager.find(User.class, id);
		return u;
	}

	public List<User> listAll() {

		return manager.createQuery("select u from User u where 1 = 1", User.class).getResultList();

	}

	/**
	 * @param active
	 * @param type
	 * @param category
	 * @param level
	 * @return
	 */
	public User listwithParams(String user, String pass) {

		User u;
		int index = 1;
		List<Object> parameters = new LinkedList<>();

		String strQuery = "select u from User u where 1 = 1 ";

		if (user != null && !user.isEmpty()) {
			strQuery = strQuery.concat("and u.email = ?" + index + " ");
			parameters.add(user);
			index++;
		}

		if (pass != null && !pass.isEmpty()) {
			strQuery = strQuery.concat("and u.password = ?" + index + " ");
			parameters.add(pass);
			index++;
		}

		Query query = manager.createQuery(strQuery, User.class);

		for (int i = 0; i < parameters.size(); i++) {
			query.setParameter(i + 1, parameters.get(i));
		}

		try {

			u = (User) query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}

		return u;

	}

}
