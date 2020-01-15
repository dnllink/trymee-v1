package br.com.bonaldoapps.trymee.rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.bonaldoapps.trymee.entity.dto.Credentials;
import br.com.bonaldoapps.trymee.entity.dto.SubscriptionDTO;
import br.com.bonaldoapps.trymee.rest.filter.RequiresAuthToken;
import br.com.bonaldoapps.trymee.service.SubscriptionService;

@Path("/subscriptions")
@Stateless
public class SubscriptionResource extends BaseResource {

	@Inject
	private SubscriptionService subscriptionService;

	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@RequiresAuthToken
	public Response listWithParams() {

		Long idUser = getUserId();
		Response result = null;

		SubscriptionDTO sub = subscriptionService.findActiveSubscription(idUser);

		if (sub == null) {
			result = Response.serverError().entity("Ocorreu um erro ao carregar os dados, tente novamente.").build();
		} else {
			result = Response.ok(sub).build();
		}

		return result;

	}

	@Path("/")
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	@RequiresAuthToken
	public Response updatePassword(Credentials credentials) {

		Long idUser = getUserId();
		Response result = Response.ok().build();
		String password = credentials.getPass();

		if (password == null || password.isEmpty()) {
			result = Response.serverError().entity("Não foi informada a nova senha.").build();
		} else {
			try {

				subscriptionService.updateUserPassword(idUser, password);

			} catch (Exception e) {

				result = Response.serverError().entity(e.getMessage()).build();

			}
		}

		return result;

	}

}