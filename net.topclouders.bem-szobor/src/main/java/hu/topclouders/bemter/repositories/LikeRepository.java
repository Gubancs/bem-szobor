package hu.topclouders.bemter.repositories;

import hu.topclouders.bemter.domain.Like;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface LikeRepository extends MongoRepository<Like, String>,
		QueryDslPredicateExecutor<Like> {

}
