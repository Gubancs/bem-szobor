package hu.topclouders.bemszobor.repositories;

import hu.topclouders.bemszobor.domain.Demonstration;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface DemonstrationRepository extends
		MongoRepository<Demonstration, String>,
		QueryDslPredicateExecutor<Demonstration> {

}
