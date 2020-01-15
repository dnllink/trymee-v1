/**
 * 
 */
package br.com.bonaldoapps.trymee.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.bonaldoapps.trymee.dao.CandidateDAO;
import br.com.bonaldoapps.trymee.dao.ProcessDAO;
import br.com.bonaldoapps.trymee.dao.TestDAO;
import br.com.bonaldoapps.trymee.dao.UserDAO;
import br.com.bonaldoapps.trymee.entity.Candidate;
import br.com.bonaldoapps.trymee.entity.Process;
import br.com.bonaldoapps.trymee.entity.Test;
import br.com.bonaldoapps.trymee.entity.dto.ProcessDTO;
import br.com.bonaldoapps.trymee.entity.dto.ProcessSummaryDTO;
import br.com.bonaldoapps.trymee.service.facade.ProcessSummaryFacade;
import br.com.bonaldoapps.trymee.service.facade.SendLinkFacade;

/**
 * @author Daniel
 *
 */
@Stateless
public class ProcessService {

	@Inject
	private ProcessDAO processDAO;

	@Inject
	private TestDAO testDAO;

	@Inject
	private CandidateDAO candidateDAO;

	@Inject
	private SendLinkFacade sendLinkFacade;

	@Inject
	private ProcessSummaryFacade processSummaryFacade;

	@Inject
	private UserDAO userDAO;

	/**
	 * @param activeParam
	 * @param level
	 * @param name
	 * @param description
	 * @return
	 */
	public List<ProcessDTO> listWithParams(Boolean activeParam, Calendar startDate, Calendar finalDate,
			String description, Long idUser, Long startPagination, Long endPagination) {

		Date startDt = null;
		Date finalDt = null;

		if (startDate != null) {
			startDt = startDate.getTime();
		}

		if (finalDate != null) {
			finalDt = finalDate.getTime();
		}

		List<Process> processes = processDAO.listWithParams(activeParam, startDt, finalDt, description, idUser, startPagination, endPagination);

		return convertToDTOList(processes);

	}

	/**
	 * @param id
	 * @param b
	 * @param c
	 * @param d
	 * @return
	 */
	public ProcessDTO find(Long id, Long idUSer, Boolean eagerLinks, Boolean eagerCandidates) {

		Process entity = processDAO.find(id, idUSer, eagerLinks, eagerCandidates);

		return new ProcessDTO(entity);
	}

	/**
	 * @param process
	 * @return
	 * @throws Exception
	 */
	public ProcessDTO save(Process process, Long idUser) throws Exception {

		if (process == null)
			return null;

		Test test;

		process.updateStartDateFromString();

		if (process.getTest() != null) {
			if (process.getTest().getId() != null) {
				test = testDAO.find(process.getTest().getId(), idUser, false, false);
			} else {
				test = testDAO.save(process.getTest());
			}

			process.setTest(test);
		}

		if (process.getCandidates() != null) {

			List<Candidate> candidates = process.getCandidates();
			process.setCandidates(new ArrayList<Candidate>());

			for (Candidate c : candidates) {

				if (c.getId() != null) {
					c = candidateDAO.find(c.getId(), idUser, true, false, false);
				} else {
					candidateDAO.save(c);
				}

				process.addCandidate(c);

			}

		}

		process.setUser(userDAO.find(idUser));
		processDAO.save(process);

		return new ProcessDTO(process);

	}

	/**
	 * @param id
	 */
	public void delete(Long id, Long idUSer) {

		Process toDelete = processDAO.find(id, idUSer, false, false);

		if (toDelete != null) {
			toDelete.setActive(false);
			toDelete.setEndDate(new Date());
		}

		processDAO.update(toDelete);

	}

	/**
	 * @param id
	 */
	public void activate(Long id, Long idUSer) {

		Process toUpdate = processDAO.find(id, idUSer, false, false);

		if (toUpdate != null) {
			toUpdate.setActive(true);
			toUpdate.setEndDate(null);
		}

		processDAO.update(toUpdate);

	}

	/**
	 * @param process
	 */
	public ProcessDTO update(Process process, Long idUser) {

		process.setUser(userDAO.find(idUser));
		processDAO.update(process);

		return new ProcessDTO(process);

	}

	/**
	 * @param processs
	 * @return
	 */
	private List<ProcessDTO> convertToDTOList(List<Process> processes) {

		List<ProcessDTO> dtos = new LinkedList<ProcessDTO>();

		for (Process p : processes) {
			dtos.add(new ProcessDTO(p));
		}

		return dtos;

	}

	public String sendLink(Long idProcess, Long idCandidate, Boolean resend, Long idUser, String host)
			throws Exception {

		return sendLinkFacade.sendLink(idProcess, idCandidate, resend, idUser, host);

	}

	public ProcessSummaryDTO findSummary(Long idProcess, Long idUSer) {

		candidateDAO.listByProcessWithLinks(idProcess, idUSer);

		return processSummaryFacade.getSummary(idProcess, idUSer);

	}
}
