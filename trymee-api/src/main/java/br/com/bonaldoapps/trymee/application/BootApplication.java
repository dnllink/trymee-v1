/**
 * 
 */
package br.com.bonaldoapps.trymee.application;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.datasources.DatasourcesFraction;
import org.wildfly.swarm.jaxrs.JAXRSArchive;
import org.wildfly.swarm.jpa.JPAFraction;

import br.com.bonaldoapps.trymee.dao.QuestionDAO;
import br.com.bonaldoapps.trymee.entity.Question;
import br.com.bonaldoapps.trymee.entity.dto.QuestionDTO;
import br.com.bonaldoapps.trymee.entity.enums.QuestionTypeEnum;
import br.com.bonaldoapps.trymee.exception.InvalidLinkException;
import br.com.bonaldoapps.trymee.rest.RestApplication;
import br.com.bonaldoapps.trymee.rest.filter.AuthFilter;
import br.com.bonaldoapps.trymee.rest.filter.CORSFilter;
import br.com.bonaldoapps.trymee.service.QuestionService;
import br.com.bonaldoapps.trymee.service.facade.SendEmailFacade;

/**
 * @author Daniel
 *
 */
public class BootApplication {

	public static void main(String[] args) throws Exception {

		Swarm swarm = new Swarm(args);

		swarm.fraction(new DatasourcesFraction().jdbcDriver("com.mysql", (d) -> {
			d.driverClassName("com.mysql.jdbc.Driver");
			d.xaDatasourceClass("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");
			d.driverModuleName("com.mysql");
		}).dataSource("logicDSVDataSource", (ds) -> {
			ds.driverName("com.mysql");
			ds.connectionUrl("jdbc:mysql://localhost:3306/bonald_logic_dsv");
			// ds.connectionUrl("jdbc:mysql://107.170.21.233:3306/bonald_logic_dsv");
			ds.userName("bonald_admin");
			ds.password("bonald_admin");
		}));

		swarm.fraction(new JPAFraction().defaultDatasource("logicDSVDataSource"));

		swarm.start();

		JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class);

		deployment.setContextRoot("trymee");
		deployment.addAsWebInfResource(
				new ClassLoaderAsset("META-INF/persistence.xml", BootApplication.class.getClassLoader()),
				"classes/META-INF/persistence.xml");

		deployment.addPackage(BootApplication.class.getPackage());
		deployment.addPackage(QuestionDAO.class.getPackage());
		deployment.addPackage(Question.class.getPackage());
		deployment.addPackage(QuestionTypeEnum.class.getPackage());
		deployment.addPackage(RestApplication.class.getPackage());
		deployment.addPackage(QuestionDTO.class.getPackage());
		deployment.addPackage(QuestionService.class.getPackage());
		deployment.addPackage(SendEmailFacade.class.getPackage());
		deployment.addPackage(InvalidLinkException.class.getPackage());
		deployment.addPackage(AuthFilter.class.getPackage());

		deployment.addResource(CORSFilter.class);

		deployment.addAllDependencies();

		swarm.deploy(deployment);

	}

}
