package hu.topclouders.bemszobor.dao.impl;

import org.springframework.stereotype.Repository;

import hu.topclouders.bemszobor.dao.IProtestRepository;
import hu.topclouders.bemszobor.domain.Demonstration;

@Repository
public class ProtestDao extends AbstractJpaDao<Demonstration> implements
		IProtestRepository {

}
