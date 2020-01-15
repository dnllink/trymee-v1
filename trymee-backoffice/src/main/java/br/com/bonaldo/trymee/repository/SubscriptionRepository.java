package br.com.bonaldo.trymee.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.bonaldo.trymee.entity.Subscription;

@RepositoryRestResource(collectionResourceRel = "subscription", path = "subscriptions")
public interface SubscriptionRepository extends PagingAndSortingRepository<Subscription, Long> {

}
