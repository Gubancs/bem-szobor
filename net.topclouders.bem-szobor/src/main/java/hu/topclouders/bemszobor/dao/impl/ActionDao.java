package hu.topclouders.bemszobor.dao.impl;

import org.springframework.stereotype.Repository;

import hu.topclouders.bemszobor.dao.IActionRepository;
import hu.topclouders.bemszobor.domain.Action;

@Repository
public class ActionDao extends AbstractJpaDao<Action> implements
		IActionRepository {

}
