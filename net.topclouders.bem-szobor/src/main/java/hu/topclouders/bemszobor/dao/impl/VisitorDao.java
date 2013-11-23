package hu.topclouders.bemszobor.dao.impl;

import hu.topclouders.bemszobor.dao.IVisitorRepository;
import hu.topclouders.bemszobor.domain.Visitor;

import org.springframework.stereotype.Repository;

@Repository
public class VisitorDao extends AbstractJpaDao<Visitor> implements
		IVisitorRepository {

}
