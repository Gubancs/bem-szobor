package hu.topclouders.bemszobor.repositories;

import hu.topclouders.bemszobor.domain.Action;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface ActionRepository extends MongoRepository<Action, String>,
		QueryDslPredicateExecutor<Action> {

}
