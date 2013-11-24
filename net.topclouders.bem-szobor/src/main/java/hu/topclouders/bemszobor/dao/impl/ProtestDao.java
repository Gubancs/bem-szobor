package hu.topclouders.bemszobor.dao.impl;

import static hu.topclouders.bemszobor.predicates.ProtestPredicate.*;
import hu.topclouders.bemszobor.dao.IProtestRepository;
import hu.topclouders.bemszobor.domain.Demonstration;
import hu.topclouders.bemszobor.domain.QAction;
import hu.topclouders.bemszobor.enums.ActionType;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.Projections;

@Repository
public class ProtestDao extends AbstractJpaDao<Demonstration> implements
		IProtestRepository {

	private static final Integer PAGE_LIMIT = 10;

	@Override
	public List<Tuple> getActiveDemonstrators(Date date) {
		QAction qAction = QAction.action;
		JPAQuery query = new JPAQuery(getEntityManager()).from(qAction);
		return query
				.where(qAction.actionType.eq(ActionType.JOIN).and(
						isActiveProtest(qAction.protest, date)))
				.groupBy(qAction.protest.id)
				.orderBy(qAction.protest.start.desc(),
						qAction.protest.name.desc()).limit(PAGE_LIMIT)
				.list(Projections.tuple(qAction.protest, qAction.count()));
	}

	@Override
	public List<Tuple> getClosedProtests(Date date) {
		QAction qAction = QAction.action;

		return new JPAQuery(getEntityManager())
				.from(qAction)
				.where(qAction.actionType.eq(ActionType.DEMONSTRATOR).and(
						isClosedProtest(qAction.protest, date)))
				.groupBy(qAction.protest.id, qAction.date)
				.orderBy(qAction.protest.start.desc()).limit(PAGE_LIMIT)
				.list(qAction.protest, qAction.value.sum());
	}

	@Override
	public List<Tuple> getInProgressProtests(Date date) {
		QAction qAction = QAction.action;

		JPAQuery query = new JPAQuery(getEntityManager()).from(qAction);
		return query
				.where(qAction.actionType.eq(ActionType.DEMONSTRATOR).and(
						isInProgressProtest(qAction.protest, date)))
				.groupBy(qAction.protest).orderBy(qAction.protest.name.desc())
				.limit(PAGE_LIMIT).list(qAction.protest, qAction.value.sum());
	}
}
