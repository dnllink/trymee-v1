/**
 * 
 */
package br.com.bonaldoapps.trymee.email.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.bonaldoapps.trymee.email.entity.Recipient;
import br.com.bonaldoapps.trymee.email.interf.TMEmail;

/**
 * @author Daniel
 *
 */
public class TestLinkEmail implements TMEmail {

	private static final String TEMPLATE = "tm.mail.template.test-link";

	private String name;
	private String email;
	private String link;
	private String sender;

	/**
	 * 
	 */
	public TestLinkEmail(String name, String email, String sender, String link) {

		this.name = name;
		this.email = email;
		this.sender = sender;
		this.link = link;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.bonaldoapps.trymee.email.interf.SPEmail#recipients()
	 */
	@Override
	public List<Recipient> recipients() {
		Recipient r = new Recipient(email, name);
		List<Recipient> rs = new ArrayList<>();
		rs.add(r);
		return rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.bonaldoapps.trymee.email.interf.SPEmail#template()
	 */
	@Override
	public String template() {
		return TEMPLATE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.bonaldoapps.trymee.email.interf.SPEmail#data()
	 */
	@Override
	public Map<String, String> data() {

		Map<String, String> data = new HashMap<>();
		data.put("name", name);
		data.put("email", email);
		data.put("link", link);
		data.put("sender", sender);

		return data;
	}

	/* (non-Javadoc)
	 * @see br.com.bonaldoapps.trymee.email.interf.SPEmail#from()
	 */
	@Override
	public String from() {
		return "naoresponda@trymee.com.br";
	}

	/* (non-Javadoc)
	 * @see br.com.bonaldoapps.trymee.email.interf.SPEmail#fromName()
	 */
	@Override
	public String fromName() {
		return "Trymee App";
	}

	/* (non-Javadoc)
	 * @see br.com.bonaldoapps.trymee.email.interf.SPEmail#subject()
	 */
	@Override
	public String subject() {
		return "Responder prova - Trymee App";
	}

}
