/**
 * 
 */
package br.com.bonaldoapps.trymee.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.bonaldoapps.trymee.dao.UserDAO;
import br.com.bonaldoapps.trymee.entity.Process;
import br.com.bonaldoapps.trymee.entity.User;
import br.com.bonaldoapps.trymee.entity.dto.ProcessDTO;
import br.com.bonaldoapps.trymee.entity.dto.UserDTO;
import br.com.bonaldoapps.trymee.service.TutorialService;

/**
 * @author Daniel
 *
 */
@Path("/info")
@Stateless
public class TrymeeInfoResource {

	private static final String TOKEN = "tm-valid-token";

	@Inject
	private TutorialService tutoService;

	@Inject
	private UserDAO userDAO;

	@Path("/users")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
	public Response listUsers(@QueryParam("token") String token) {

		if (token == null || token.isEmpty())
			return Response.serverError().build();

		if (!TOKEN.equals(token))
			return Response.serverError().build();

		List<UserDTO> dtos = new ArrayList<>();

		for (User u : userDAO.listAll()) {
			dtos.add(new UserDTO(u));
		}

		return Response.ok(dtos).build();

	}

	@GET
	@Path("/tutorial")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response createTutorial() {

		Process p = tutoService.createTutorial(new User());

		ProcessDTO dto = new ProcessDTO(p);

		return Response.ok(dto).build();

	}

}
