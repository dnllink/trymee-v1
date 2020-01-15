/**
 * 
 */
package br.com.bonaldoapps.trymee.rest;

import java.util.LinkedList;
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

import br.com.bonaldoapps.trymee.entity.Test;
import br.com.bonaldoapps.trymee.entity.dto.TestDTO;
import br.com.bonaldoapps.trymee.rest.filter.RequiresAuthToken;
import br.com.bonaldoapps.trymee.service.TestService;

/**
 * @author Daniel
 *
 */
@Path("/tests")
@Stateless
public class TestResource extends BaseResource {

	@Inject
	private TestService testService;

	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@RequiresAuthToken
	public Response listWithParams(@QueryParam("level") Long level, @QueryParam("active") Long active,
			@QueryParam("name") String name, @QueryParam("description") String description,
			@QueryParam("searchText") String searchText) {

		Long idUser = getUserId();

		List<TestDTO> dtos = new LinkedList<TestDTO>();

		if (searchText != null && !searchText.isEmpty()) {

			dtos = testService.listByText(searchText, idUser);

		} else {

			Boolean activeParam = true;

			if (active != null) {
				activeParam = (active == 1 ? true : false);
			}

			dtos = testService.listWithParams(activeParam, level, name, description, idUser);

		}

		return Response.ok(dtos).build();

	}

	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@RequiresAuthToken
	public Response find(@PathParam("id") long id) {

		Long idUser = getUserId();

		return Response.ok(testService.find(id, true, true, idUser)).build();

	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@RequiresAuthToken
	public Response create(Test test) {

		Long idUser = getUserId();

		return Response.created(null).entity(testService.save(test, idUser)).build();

	}

	@Path("/{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@RequiresAuthToken
	public Response delete(@PathParam("id") long id) {

		Long idUser = getUserId();

		testService.delete(id, idUser);

		return Response.ok().build();

	}

	@Path("/")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@RequiresAuthToken
	public Response activate(Test test) {

		Long idUser = getUserId();

		testService.update(test, idUser);

		return Response.ok().build();

	}

	@POST
	@Path("/generate")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@RequiresAuthToken
	public TestDTO generate(Test test) {

		return new TestDTO(test);

	}

}
