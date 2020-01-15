/**
 * 
 */
package br.com.bonaldoapps.trymee.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.bonaldoapps.trymee.entity.Answer;

/**
 * @author Daniel
 *
 */
@Stateless
public class AnswerDAO {

	@PersistenceContext
	EntityManager manager;

	public Answer save(Answer answer) {

		manager.persist(answer);
		return answer;

	}

	public Answer update(Answer answer) {

		manager.merge(answer);
		return answer;

	}

	@SuppressWarnings("unchecked")
	public List<Answer> listWithParameters(Long idCandidate, Long idProcess, Boolean corrected) {

		HashMap<String, Object> parameters = new HashMap<String, Object>();

		StringBuilder sb = new StringBuilder("select a from Answer a where 1=1 ");

		if (idCandidate != null) {
			sb.append(" and a.candidate.id = :idCandidateParam ");
			parameters.put("idCandidateParam", idCandidate);
		}

		if (idProcess != null) {
			sb.append(" and a.process.id = :idProcessParam ");
			parameters.put("idProcessParam", idProcess);
		}

		if (corrected != null) {
			if (corrected) {
				sb.append(" and a.correct not null ");
			} else {
				sb.append(" and a.correct is null ");
			}

		}

		Query query = manager.createQuery(sb.toString(), Answer.class);

		for (Map.Entry<String, Object> entry : parameters.entrySet()) {

			query.setParameter(entry.getKey(), entry.getValue());

		}

		return query.getResultList();
	}

}
