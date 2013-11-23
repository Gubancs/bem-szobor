package hu.topclouders.bemszobor.repositories;

import hu.topclouders.bemszobor.domain.Visitor;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface VisitorRepository extends MongoRepository<Visitor, String>,
		QueryDslPredicateExecutor<Visitor> {

}
