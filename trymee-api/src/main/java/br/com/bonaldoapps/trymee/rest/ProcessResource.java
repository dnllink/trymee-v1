/**
 * 
 */
package br.com.bonaldoapps.trymee.rest;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.bonaldoapps.trymee.entity.Process;
import br.com.bonaldoapps.trymee.entity.dto.ProcessDTO;
import br.com.bonaldoapps.trymee.rest.filter.RequiresAuthToken;
import br.com.bonaldoapps.trymee.service.CandidateService;
import br.com.bonaldoapps.trymee.service.ProcessService;
import br.com.bonaldoapps.trymee.service.facade.PropertiesFacade;

/**
 * @author Daniel
 *
 */
@Path("/processes")
@Stateless
public class ProcessResource extends BaseResource {

	@Inject
	private ProcessService processService;

	@Inject
	private CandidateService candidateService;

	@Inject
	private PropertiesFacade prop;

	@Path("/")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
	@RequiresAuthToken
	public Response listWithParams(@QueryParam("active") Long active, @QueryParam("startDate") String startDate,
			@QueryParam("finalDate") String finalDate, @QueryParam("description") String description,
			@QueryParam("startPagination") Long startPagination, @QueryParam("endPagination") Long endPagination) {

		Long idUser = getUserId();

		Boolean activeParam = null;

		if (active != null) {
			activeParam = (active == 1 ? true : false);
		}

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		Calendar startDt = null;
		Calendar finalDt = null;

		try {

			if (startDate != null && !startDate.isEmpty()) {
				startDt = Calendar.getInstance();
				startDt.setTime(sdf.parse(startDate));
			}

			if (finalDate != null && !finalDate.isEmpty()) {
				finalDt = Calendar.getInstance();
				finalDt.setTime(sdf.parse(finalDate));
			}

		} catch (ParseException e) {

			return Response.serverError().type(MediaType.TEXT_PLAIN).entity("Formato de data informado inválido.")
					.build();

		}

		return Response.ok(processService.listWithParams(activeParam, startDt, finalDt, description, idUser, startPagination, endPagination))
				.type(MediaType.APPLICATION_JSON).build();
	}

	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@RequiresAuthToken
	public ProcessDTO find(@PathParam("id") long id) {

		Long idUSer = getUserId();

		return processService.find(id, idUSer, true, true);

	}

	@Path("/summary/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@RequiresAuthToken
	public Response findSummary(@PathParam("id") Long id) {

		Long idUSer = getUserId();

		return Response.ok(processService.findSummary(id, idUSer)).build();

	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
	@RequiresAuthToken
	public Response create(Process process) {

		Response resp = null;

		Long idUser = getUserId();

		try {
			ProcessDTO dto = processService.save(process, idUser);
			resp = Response.ok(dto).build();
		} catch (Exception e) {
			resp = Response.serverError().entity(e.getMessage()).type(MediaType.TEXT_PLAIN).build();
		}

		return resp;

	}

	@Path("/{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@RequiresAuthToken
	public Response delete(@PathParam("id") long id) {

		Long idUSer = getUserId();

		processService.delete(id, idUSer);

		return Response.ok().build();

	}

	@Path("/activate/{id}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@RequiresAuthToken
	public Response activate(@PathParam("id") long id) {

		Long idUSer = getUserId();

		processService.activate(id, idUSer);

		return Response.ok().build();

	}

	@Path("/")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@RequiresAuthToken
	public Response activate(Process process) {

		Long idUser = getUserId();

		processService.update(process, idUser);

		return Response.ok().build();

	}

	@Path("/links")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@RequiresAuthToken
	public Response enviarLinksPendentes(@QueryParam("idProcess") Long idProcess,
			@QueryParam("idCandidate") Long idCandidate, @QueryParam("resend") Boolean resend) {

		Long idUser = getUserId();
		String host = null;
		try {
			Properties properties = prop.readProperties();
			host = properties.getProperty("tm.mail.url");
		} catch (IOException e1) {
			host = getApiHost();
		}

		Response result;
		String message;

		try {
			message = processService.sendLink(idProcess, idCandidate, resend, idUser, host);

			if (message != null && !message.isEmpty()) {
				result = Response.serverError().entity(message).type(MediaType.TEXT_PLAIN).build();
			} else {
				result = Response.created(null).build();
			}
		} catch (Exception e) {
			result = Response.serverError().entity(e.getMessage()).type(MediaType.TEXT_PLAIN).build();
		}

		return result;

	}

	@Path("/candidates/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@RequiresAuthToken
	public Response getCandidatesWithLinks(@PathParam("id") long idProcess) {

		Long idUser = getUserId();

		return Response.ok().entity(candidateService.findCandidatesWithLinks(idProcess, idUser)).build();

	}

}
