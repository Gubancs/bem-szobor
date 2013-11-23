package hu.topclouders.bemter.repositories;

import hu.topclouders.bemter.domain.Visitor;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface VisitorRepository extends MongoRepository<Visitor, String>,
		QueryDslPredicateExecutor<Visitor> {

}
