package hu.topclouders.bemszobor.dao.impl;

import static hu.topclouders.bemszobor.predicates.ProtestPredicate.*;
import hu.topclouders.bemszobor.dao.IDemonstrationDao;
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
public class DemonstrationDao extends AbstractJpaDao<Demonstration> implements
		IDemonstrationDao {

	private static final Integer PAGE_LIMIT = 10;

	@Override
	public List<Tuple> getActiveDemonstrators(Date date) {
		QAction qAction = QAction.action;
		JPAQuery query = new JPAQuery(getEntityManager()).from(qAction);
		return query
				.where(qAction.actionType.eq(ActionType.JOIN).and(
						isActiveDemonstration(qAction.demonstration, date)))
				.groupBy(qAction.demonstration.id)
				.orderBy(qAction.demonstration.start.desc(),
						qAction.demonstration.name.desc())
				.limit(PAGE_LIMIT)
				.list(Projections.tuple(qAction.demonstration, qAction.count()));
	}

	@Override
	public List<Tuple> getClosedDemonstrations(Date date) {
		QAction qAction = QAction.action;

		return new JPAQuery(getEntityManager())
				.from(qAction)
				.where(qAction.actionType.eq(ActionType.DEMONSTRATOR).and(
						isClosedDemonstration(qAction.demonstration, date)))
				.groupBy(qAction.demonstration.id, qAction.date)
				.orderBy(qAction.demonstration.start.desc()).limit(PAGE_LIMIT)
				.list(qAction.demonstration, qAction.value.sum());
	}

	@Override
	public List<Tuple> getInProgressProtests(Date date) {
		QAction qAction = QAction.action;

		JPAQuery query = new JPAQuery(getEntityManager()).from(qAction);
		return query
				.where(qAction.actionType.eq(ActionType.DEMONSTRATOR).and(
						isInProgressDemonstration(qAction.demonstration, date)))
				.groupBy(qAction.demonstration)
				.orderBy(qAction.demonstration.name.desc()).limit(PAGE_LIMIT)
				.list(qAction.demonstration, qAction.value.sum());
	}
}
