/**
 * 
 */
package br.com.bonaldoapps.trymee.service.facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.bonaldoapps.trymee.dao.ProcessDAO;
import br.com.bonaldoapps.trymee.dao.TestGradeDAO;
import br.com.bonaldoapps.trymee.entity.Link;
import br.com.bonaldoapps.trymee.entity.Process;
import br.com.bonaldoapps.trymee.entity.TestGrade;
import br.com.bonaldoapps.trymee.entity.dto.ProcessSummaryDTO;

/**
 * @author Daniel
 *
 */
@Stateless
public class ProcessSummaryFacade {

	@Inject
	private ProcessDAO processDAO;

	@Inject
	private TestGradeDAO testGradeDAO;

	private ProcessSummaryDTO summary = new ProcessSummaryDTO();

	public ProcessSummaryDTO getSummary(Long idProcess, Long idUSer) {

		Process process = processDAO.find(idProcess, idUSer, true, true);

		summary.setDescription(process.getDescription());
		summary.setId(process.getId());
		summary.setQtCandidates(new Long(process.getCandidates().size()));
		summary.setQtSent(new Long(process.getLinks().size()));
		summary.setIdTest(process.getTest() != null ? process.getTest().getId() : null);
		summary.setActive(process.getActive());

		summary.setQtAnswered(0l);
		summary.setQtPending(0l);

		for (Link l : process.getLinks()) {

			if (l.getActive()) {
				summary.setQtPending(summary.getQtPending() + 1);
			} else {
				summary.setQtAnswered(summary.getQtAnswered() + 1);
			}

		}

		List<TestGrade> grades = testGradeDAO.listWithParams(idProcess, null);

		summary.setQtCorrected(0l);
		summary.setAverageRating(0d);

		for (TestGrade t : grades) {

			if (!t.getPartial()) {
				summary.setQtCorrected(summary.getQtCorrected() + 1);
			}

			summary.setAverageRating(summary.getAverageRating() + t.getRating());

		}

		summary.setAverageRating(summary.getAverageRating() / grades.size());

		return summary;

	}

}
