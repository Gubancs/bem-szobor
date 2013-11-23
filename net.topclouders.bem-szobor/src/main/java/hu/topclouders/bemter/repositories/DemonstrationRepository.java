package hu.topclouders.bemter.repositories;

import hu.topclouders.bemter.domain.Demonstration;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface DemonstrationRepository extends
		MongoRepository<Demonstration, String>,
		QueryDslPredicateExecutor<Demonstration> {

}
