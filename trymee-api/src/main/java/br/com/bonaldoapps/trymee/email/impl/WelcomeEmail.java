/**
 * 
 */
package br.com.bonaldoapps.trymee.email.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.bonaldoapps.trymee.email.entity.Recipient;
import br.com.bonaldoapps.trymee.email.interf.TMEmail;

/**
 * @author Daniel
 *
 */
public class WelcomeEmail implements TMEmail {

	private static final String TEMPLATE = "tm.mail.template.welcome";

	private String name;
	private String email;
	private String plano;
	private String dataFim;
	private String quantidade;
	private String price;

	/**
	 * 
	 */
	public WelcomeEmail(String name, String email, String plano, Date dataFim, Long quantidade, Double price) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat df = new DecimalFormat("#,##0.00");

		this.name = name;
		this.email = email;
		this.plano = plano;
		this.dataFim = sdf.format(dataFim);
		this.quantidade = String.valueOf(quantidade);
		this.price = "R$ " + df.format(price);

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
		data.put("plan", plano);
		data.put("endDate", dataFim);
		data.put("quantity", quantidade);
		data.put("price", price);

		return data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.bonaldoapps.trymee.email.interf.SPEmail#from()
	 */
	@Override
	public String from() {
		return "contato@trymee.com.br";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.bonaldoapps.trymee.email.interf.SPEmail#fromName()
	 */
	@Override
	public String fromName() {
		return "Trymee App";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.bonaldoapps.trymee.email.interf.SPEmail#subject()
	 */
	@Override
	public String subject() {
		return "Bem vindo ao Trymee!";
	}

}
