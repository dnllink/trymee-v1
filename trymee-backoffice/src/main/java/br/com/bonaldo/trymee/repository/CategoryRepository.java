package br.com.bonaldo.trymee.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.bonaldo.trymee.entity.Category;

@RepositoryRestResource(collectionResourceRel = "category", path = "categories")
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {

	public Category findByCode(@Param("code") String code);

}
