/**
 * 
 */
package br.com.bonaldoapps.trymee.dao;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.bonaldoapps.trymee.entity.Token;

/**
 * @author Daniel
 *
 */
@Stateless
public class TokenDAO extends BaseDAO {

	@PersistenceContext
	EntityManager manager;

	public Token save(Token token) {
		manager.persist(token);
		return token;
	}

	public Token update(Token token) {
		manager.merge(token);
		return token;
	}

	public Token find(Long id) {

		Token t = manager.find(Token.class, id);
		return t;
	}

	public Token listWithParams(String token, Long idUser) {

		Map<String, Object> parameters = new HashMap<String, Object>();

		Token t;

		StringBuilder sb = new StringBuilder("select t from Token t where 1 = 1 ");

		if (token != null) {
			sb.append("and t.token = :tokenParam ");
			parameters.put("tokenParam", token);
		}

		if (idUser != null) {
			sb.append("and t.user.id = :idUserParam ");
			parameters.put("idUserParam", idUser);
		}

		Query query = manager.createQuery(sb.toString(), Token.class);

		addQueryParameters(parameters, query);

		try {
			t = (Token) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		} catch (NonUniqueResultException nue) {
			return null;
		}

		return t;

	}

}
