package br.com.bonaldo.trymee;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy.RepositoryDetectionStrategies;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.stereotype.Component;

import br.com.bonaldo.trymee.entity.Category;
import br.com.bonaldo.trymee.entity.Level;
import br.com.bonaldo.trymee.entity.Plan;

@Component
public class SpringDataRestCustomization extends RepositoryRestConfigurerAdapter {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {

		config.getCorsRegistry().addMapping("/api/**").allowedOrigins("*");
		config.setRepositoryDetectionStrategy(RepositoryDetectionStrategies.ANNOTATED);
		config.exposeIdsFor(Plan.class, Level.class, Category.class);
		super.configureRepositoryRestConfiguration(config);

	}

}
