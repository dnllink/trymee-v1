/**
 * 
 */
package br.com.bonaldoapps.trymee.rest;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.bonaldoapps.trymee.dao.AnswerDAO;
import br.com.bonaldoapps.trymee.dao.TestGradeDAO;
import br.com.bonaldoapps.trymee.entity.Answer;
import br.com.bonaldoapps.trymee.entity.TestGrade;
import br.com.bonaldoapps.trymee.entity.dto.AnswerDTO;
import br.com.bonaldoapps.trymee.service.facade.CorrectTestFacade;

/**
 * @author Daniel
 *
 */
@Path("/answers")
@Stateless
public class AnswerResource extends BaseResource {

	@Inject
	private AnswerDAO answerDAO;

	@Inject
	private CorrectTestFacade correctTest;

	@Inject
	private TestGradeDAO testGradeDAO;

	@Path("/")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
	public Response listWithParams(@QueryParam("idProcess") Long idProcess,
			@QueryParam("idCandidate") Long idCandidate) {

		List<AnswerDTO> dtos = new LinkedList<>();

		for (Answer a : answerDAO.listWithParameters(idCandidate, idProcess, false)) {

			dtos.add(new AnswerDTO(a));

		}

		return Response.ok(dtos).type(MediaType.APPLICATION_JSON).build();
	}

	@Path("/")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response activate(Answer[] answers) {

		if (answers != null && answers.length > 0) {

			Long idProcess = answers[0].getProcess().getId();
			Long idCandidate = answers[0].getCandidate().getId();

			for (Answer a : answers) {
				answerDAO.update(a);
			}

			List<TestGrade> grades = testGradeDAO.listWithParams(idProcess, idCandidate);

			if (grades != null && grades.size() == 1) {
				correctTest.updateGrade(grades.get(0).getId());
			}

		}

		return Response.ok().build();

	}

}
