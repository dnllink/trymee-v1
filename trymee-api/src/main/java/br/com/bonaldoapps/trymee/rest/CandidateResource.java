package br.com.bonaldoapps.trymee.rest;

import java.util.List;

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

import br.com.bonaldoapps.trymee.entity.Candidate;
import br.com.bonaldoapps.trymee.entity.dto.CandidateDTO;
import br.com.bonaldoapps.trymee.rest.filter.RequiresAuthToken;
import br.com.bonaldoapps.trymee.service.CandidateService;

@Path("/candidates")
@Stateless
public class CandidateResource extends BaseResource {

	@Inject
	private CandidateService candidateService;

	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@RequiresAuthToken
	public List<CandidateDTO> listWithParams(@QueryParam("name") String name, @QueryParam("email") String email,
			@QueryParam("active") Long active, @QueryParam("searchText") String searchText) {

		List<CandidateDTO> dtos;

		Long idUser = getUserId();

		if (searchText != null && !searchText.isEmpty()) {

			dtos = candidateService.listByText(searchText, idUser);

		} else {

			Boolean activeParam = true;

			if (active != null) {
				activeParam = (active == 1 ? true : false);
			}

			dtos = candidateService.list(name, email, activeParam, idUser);

		}

		return dtos;

	}

	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@RequiresAuthToken
	public CandidateDTO find(@PathParam("id") long id) {

		Long idUser = getUserId();

		return candidateService.find(id, idUser);

	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@RequiresAuthToken
	public CandidateDTO create(Candidate candidate) {

		Long idUser = getUserId();

		return candidateService.save(candidate, idUser);

	}

	@Path("/{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@RequiresAuthToken
	public Response delete(@PathParam("id") long id) {

		Long idUser = getUserId();

		candidateService.delete(id, idUser);

		return Response.ok().build();

	}

	@Path("/")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@RequiresAuthToken
	public Response activate(Candidate candidate) {

		Long idUser = getUserId();

		candidateService.update(candidate, idUser);

		return Response.ok().build();

	}
}