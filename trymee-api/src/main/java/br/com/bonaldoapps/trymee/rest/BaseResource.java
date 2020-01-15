package br.com.bonaldoapps.trymee.rest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

public abstract class BaseResource {

	@Context
	private HttpServletRequest req;

	protected Long getUserId() {

		Object obj = req.getAttribute("idUser");
		Long idUser = null;

		if (obj != null) {
			idUser = (Long) obj;
		}

		return idUser;

	}

	protected String getApiHost() {

		String[] url = req.getRequestURL().toString().split("/");

		String completeUrl = url[0].concat("//").concat(url[2]).concat("/");

		return completeUrl;

	}

	protected String encodeValue(String value) throws UnsupportedEncodingException {

		String encoded = URLEncoder.encode(value, StandardCharsets.UTF_8.toString());

		return encoded;

	}

}