package br.com.bonaldoapps.trymeeweb.app;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.undertow.WARArchive;

public class Boot {

	public static void main(String[] args) throws Exception {

		Swarm swarm = new Swarm(args);

		WARArchive web = ShrinkWrap.create(WARArchive.class);

		web.staticContent();
		web.setContextRoot("trymee-web");

		swarm.start();

		swarm.deploy(web);

	}

}
