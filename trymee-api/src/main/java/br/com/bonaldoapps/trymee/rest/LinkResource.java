/**
 * 
 */
package br.com.bonaldoapps.trymee.rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.bonaldoapps.trymee.entity.dto.TestToAnswerDTO;
import br.com.bonaldoapps.trymee.exception.InvalidLinkException;
import br.com.bonaldoapps.trymee.service.AnswerTestService;
import br.com.bonaldoapps.trymee.service.TestToAnswerGenerator;
import br.com.bonaldoapps.trymee.service.facade.CorrectTestFacade;

/**
 * @author Daniel
 *
 */
@Path("/links")
@Stateless
public class LinkResource {

	@Inject
	private TestToAnswerGenerator testGenerator;

	@Inject
	private AnswerTestService answerTestService;

	@Inject
	private CorrectTestFacade correctTestFacade;

	@Path("/{token}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findTest(@PathParam("token") String token) {

		TestToAnswerDTO test = null;
		Response result = null;

		try {
			test = testGenerator.getTest(token);
			result = Response.ok(test).build();
		} catch (InvalidLinkException e) {
			result = Response.serverError().entity(e.getMessage()).type(MediaType.TEXT_PLAIN).build();
		}

		return result;

	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response answerTest(TestToAnswerDTO test) {

		Response result = null;

		try {
			answerTestService.saveAnswers(test);
			correctTestFacade.correctTest(test.getT());
			result = Response.created(null).build();
		} catch (InvalidLinkException e) {
			result = Response.serverError().entity(e.getMessage()).build();
		}

		return result;

	}

}
