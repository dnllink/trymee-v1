/**
 * 
 */
package br.com.bonaldoapps.trymee.rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.bonaldoapps.trymee.entity.dto.Credentials;
import br.com.bonaldoapps.trymee.service.facade.LoginFacade;

/**
 * @author Daniel
 *
 */
@Path("/tokens")
@Stateless
public class TokenResource {

	@Inject
	private LoginFacade loginFacade;

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response login(Credentials c) {

		Response result;

		try {

			String token = loginFacade.doLogin(c.getUser(), c.getPass());
			result = Response.ok(token).type(MediaType.TEXT_PLAIN).build();

		} catch (Exception e) {
			result = Response.serverError().entity(e.getMessage()).build();
		}

		return result;

	}

}
