/**
 * 
 */
package br.com.bonaldoapps.trymee.rest;

import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.bonaldoapps.trymee.entity.Plan;
import br.com.bonaldoapps.trymee.service.SubscriptionService;
import br.com.bonaldoapps.trymee.service.facade.LoginFacade;

/**
 * @author Daniel
 *
 */
@Path("/users")
@Stateless
public class UserResource extends BaseResource {

	public static final String ERROR = "<html><head></head><body><h1>Ocorreu um erro ao efetuar o cadastro, tente novamente ou entre em contato com <strong>suporte@trymee.com.br</strong></h1><body></html>";

	@Inject
	private SubscriptionService subscriptionService;

	@Inject
	private LoginFacade loginFacade;

	@POST
	@Path("/free")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response createSubscriptionrFreePlan(@FormParam("name") String name, @FormParam("email") String email,
			@FormParam("pass") String pass, @FormParam("tel") String tel) {

//		String url = getApiHost();
		Response result;
		String errorMessage = "";
		String token = "";

		if (name == null || name.isEmpty())
			errorMessage = "Não foi informado o nome para cadastro";

		if (email == null || email.isEmpty())
			errorMessage = "Não foi informado o email para cadastro";

		if (pass == null || pass.isEmpty())
			errorMessage = "Não foi informado a senha para cadastro";

		try {

			Map<String,Long> userData = subscriptionService.createSubscription(name.trim(), email.trim(), pass.trim(), tel.trim(), Plan.FREE_PLAN);
			token = loginFacade.doLogin(email, pass);
			
			token = token.concat(" ").concat(String.valueOf(userData.get("idProcess")));

		} catch (Exception e) {

			errorMessage = e.getMessage();

		}

//		try {

			if (errorMessage.isEmpty()) {

//				result = Response.seeOther(new URI(url + "trymee-web/static/cad-ok.html")).build();
				result = Response.ok(token).build();

			} else {

//				URI uri = UriBuilder.fromPath(url + "trymee-web/static/cad-error.html")
//						.queryParam("error", errorMessage).build();
//
//				result = Response.seeOther(uri).build();
				
				result = Response.serverError().entity(errorMessage).build();

			}

//		} catch (URISyntaxException e) {
//
//			result = Response.serverError().entity(ERROR).type(MediaType.TEXT_HTML).build();
//
//		}

		return result;

	}

}
