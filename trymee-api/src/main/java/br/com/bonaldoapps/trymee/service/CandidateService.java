/**
 * 
 */
package br.com.bonaldoapps.trymee.service;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.bonaldoapps.trymee.dao.CandidateDAO;
import br.com.bonaldoapps.trymee.dao.ProcessDAO;
import br.com.bonaldoapps.trymee.dao.UserDAO;
import br.com.bonaldoapps.trymee.entity.Candidate;
import br.com.bonaldoapps.trymee.entity.Process;
import br.com.bonaldoapps.trymee.entity.dto.CandidateDTO;

/**
 * @author Daniel
 *
 */
@Stateless
public class CandidateService {

	@Inject
	private CandidateDAO candidateDAO;

	@Inject
	private UserDAO userDAO;

	@Inject
	private ProcessDAO processDAO;

	public List<CandidateDTO> list(String name, String email, Boolean active, Long idUser) {

		List<CandidateDTO> dtos = new LinkedList<>();

		for (Candidate c : candidateDAO.list(name, email, active, idUser)) {

			dtos.add(new CandidateDTO(c));

		}

		return dtos;

	}

	public List<CandidateDTO> listByText(String text, Long idUser) {

		List<CandidateDTO> dtos = new LinkedList<>();

		for (Candidate c : candidateDAO.listByText(text, idUser)) {

			dtos.add(new CandidateDTO(c));

		}

		return dtos;

	}

	public CandidateDTO find(Long id, Long idUser) {

		return new CandidateDTO(candidateDAO.find(id, idUser, false, false, false));

	}

	public CandidateDTO save(Candidate candidate, Long idUser) {

		if (candidate.getProcesses() != null) {

			List<Process> processes = new LinkedList<Process>();

			for (Process p : candidate.getProcesses()) {
				p = processDAO.find(p.getId(), idUser, false, true);
				p.getCandidates().add(candidate);
				processes.add(p);
			}

			candidate.getProcesses().clear();
			candidate.setProcesses(processes);

		}

		candidate.setUser(userDAO.find(idUser));

		return new CandidateDTO(candidateDAO.save(candidate));

	}

	public void delete(Long id, Long idUser) {

		Candidate c = candidateDAO.find(id, idUser, false, false, false);

		c.setActive(false);

		candidateDAO.update(c);

	}

	public CandidateDTO update(Candidate candidate, Long idUser) {

		candidate.setUser(userDAO.find(idUser));

		candidateDAO.update(candidate);

		return new CandidateDTO(candidate);

	}

	/**
	 * @param idProcess
	 * @param idUSer
	 * @return
	 */
	public List<CandidateDTO> findCandidatesWithLinks(Long idProcess, Long idUSer) {

		List<Candidate> candidates = candidateDAO.listByProcessWithLinks(idProcess, idUSer);

		List<CandidateDTO> dtos = new LinkedList<CandidateDTO>();

		for (Candidate c : candidates) {
			dtos.add(new CandidateDTO(c));
		}

		return dtos;
	}

}
