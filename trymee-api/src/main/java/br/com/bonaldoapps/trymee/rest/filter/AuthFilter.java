/**
 * 
 */
package br.com.bonaldoapps.trymee.rest.filter;

import java.io.IOException;
import java.util.Calendar;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import br.com.bonaldoapps.trymee.dao.TokenDAO;
import br.com.bonaldoapps.trymee.entity.Token;

/**
 * @author Daniel
 *
 */
@RequiresAuthToken
@Provider
public class AuthFilter implements ContainerRequestFilter {

	private static final String TOKEN_HEADER_NAME = "authorization";

	private static final Logger LOGGER = LogManager.getLogManager().getLogger("AuthFilter");

	private static final Response UNAUTHORIZED = Response.status(Status.UNAUTHORIZED).type(MediaType.TEXT_PLAIN)
			.entity("Acesso não autorizado.").build();

	@Inject
	private TokenDAO tokenDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.ws.rs.container.ContainerRequestFilter#filter(javax.ws.rs.container
	 * .ContainerRequestContext)
	 */
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		LOGGER.info("validando token");

		Calendar cal = Calendar.getInstance();

		String tokenStr = requestContext.getHeaderString(TOKEN_HEADER_NAME);

		Token token = tokenDAO.listWithParams(tokenStr, null);

		if (token != null) {

			if (!token.isValidToken(cal)) {

				LOGGER.info("token invalido");

				token.setActive(false);
				tokenDAO.update(token);

				requestContext.abortWith(UNAUTHORIZED);

			} else {
				LOGGER.info("token valido");
				requestContext.setProperty("idUser", token.getUser().getId());
			}

		} else {
			LOGGER.info("token nao encontrado");
			requestContext.abortWith(UNAUTHORIZED);
		}

	}

}
