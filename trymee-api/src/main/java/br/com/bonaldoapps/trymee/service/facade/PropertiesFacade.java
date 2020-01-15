package br.com.bonaldoapps.trymee.service.facade;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.ejb.Stateless;

@Stateless
public class PropertiesFacade {

	private static final String FILE_NAME = "tm.properties";
	public static final String FROM_PROPERTY = "tm.mail.link.from";
	public static final String SUBJECT_PROPERTY = "tm.mail.link.subject";
	public static final String CONTENT_PROPERTY = "tm.mail.link.content";

	public Properties readProperties() throws IOException {

		Properties prop = new Properties();

		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(FILE_NAME);

		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + FILE_NAME + "' not found in the classpath");
		}

		return prop;

	}

}