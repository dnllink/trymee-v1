package br.com.bonaldo.trymee.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.bonaldo.trymee.entity.Level;

@RepositoryRestResource(collectionResourceRel = "level", path = "levels")
public interface LevelRepository extends PagingAndSortingRepository<Level, Long> {

	public Level findByCode(@Param("code") String code);

}
