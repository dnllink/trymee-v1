package br.com.bonaldo.trymee.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.bonaldo.trymee.entity.Plan;

@RepositoryRestResource(collectionResourceRel = "plan", path = "plans")
public interface PlanRepository extends PagingAndSortingRepository<Plan, Long> {

	public Plan findByCode(@Param("code") String code);

}
