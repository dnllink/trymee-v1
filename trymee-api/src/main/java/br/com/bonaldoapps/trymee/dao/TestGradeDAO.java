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

import br.com.bonaldoapps.trymee.entity.TestGrade;

/**
 * @author Daniel
 *
 */
@Stateless
public class TestGradeDAO {

	@PersistenceContext
	EntityManager manager;

	public TestGrade save(TestGrade testGrade) {
		manager.persist(testGrade);
		return testGrade;
	}

	public TestGrade update(TestGrade testGrade) {
		manager.merge(testGrade);
		return testGrade;
	}

	public TestGrade find(Long id) {

		TestGrade t = manager.find(TestGrade.class, id);
		return t;

	}

	@SuppressWarnings("unchecked")
	public List<TestGrade> listWithParams(Long idProcess, Long idCandidate) {

		Map<String, Object> parameters = new HashMap<String, Object>();

		StringBuilder sb = new StringBuilder("select t from TestGrade t where 1 = 1 ");

		if (idProcess != null) {

			sb.append(" and t.process.id = :idProcessParam ");
			parameters.put("idProcessParam", idProcess);

		}

		if (idCandidate != null) {

			sb.append(" and t.candidate.id = :idCandidateParam ");
			parameters.put("idCandidateParam", idCandidate);

		}
		
		sb.append(" order by t.rating desc ");

		Query query = manager.createQuery(sb.toString(), TestGrade.class);

		for (Map.Entry<String, Object> entry : parameters.entrySet()) {

			query.setParameter(entry.getKey(), entry.getValue());

		}

		return query.getResultList();
	}

}
