package br.com.bonaldoapps.trymee.rest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;

import br.com.bonaldoapps.trymee.dao.TokenDAO;
import br.com.bonaldoapps.trymee.entity.Token;

@Stateless
public class AuthInterceptor {

	private static final String TOKEN_HEADER_NAME = "authorization";

	private static final Logger LOGGER = Logger.getLogger("AuthInterceptor");

	@Inject
	private HttpServletRequest req;

	@Inject
	private TokenDAO tokenDAO;

	@AroundInvoke
	public Object interceptRequest(InvocationContext ctx) throws Exception {

		LOGGER.info("Validando requisição");

		Object var;
		Calendar cal = Calendar.getInstance();

		String tokenStr = req.getHeader(TOKEN_HEADER_NAME);

		Token token = tokenDAO.listWithParams(tokenStr, null);

		if (token != null) {

			if (!token.isValidToken(cal)) {

				token.setActive(false);
				tokenDAO.update(token);

				return onInvalidToken(ctx);
			}

		} else {
			return onInvalidToken(ctx);
		}

		req.setAttribute("idUser", token.getUser().getId());

		var = ctx.proceed();

		return var;

	}

	/**
	 * @param ctx
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws Exception
	 */
	private Object onInvalidToken(InvocationContext ctx)
			throws InstantiationException, IllegalAccessException, NoSuchMethodException, Exception {
		Class<?> classTarget = ctx.getTarget().getClass();

		Object objTarget = classTarget.newInstance();

		Method method = classTarget.getMethod("onInvalidToken");

		try {
			return method.invoke(objTarget, (Object[]) null);
		} catch (InvocationTargetException e) {
			throw new Exception("Erro fatal ao proceder requisição.");
		}
	}
}
