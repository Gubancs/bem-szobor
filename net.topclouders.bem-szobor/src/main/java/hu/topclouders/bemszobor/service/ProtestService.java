package hu.topclouders.bemszobor.service;

import static hu.topclouders.bemszobor.predicates.ProtestPredicate.*;
import hu.topclouders.bemszobor.dao.IProtestRepository;
import hu.topclouders.bemszobor.domain.Protest;
import hu.topclouders.bemszobor.domain.QAction;
import hu.topclouders.bemszobor.enums.ActionType;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.Projections;

@Service
public class ProtestService {

	public static final Integer PAGE_LIMIT = 10;

	@Autowired
	private IProtestRepository demonstrationRepository;

	@PersistenceContext
	private EntityManager entityManager;

	public Map<Protest, Long> getActiveDemonstrators() {

		QAction qAction = QAction.action;

		JPAQuery query = new JPAQuery(entityManager).from(qAction);
		List<Tuple> list = query
				.where(qAction.actionType.eq(ActionType.JOIN).and(
						isActiveProtest(qAction.protest)))
				.groupBy(qAction.protest.id)
				.orderBy(qAction.protest.start.desc(),
						qAction.protest.name.desc()).limit(PAGE_LIMIT)
				.list(Projections.tuple(qAction.protest, qAction.count()));

		Map<Protest, Long> result = Maps.newHashMap();
		Protest protest;
		Long visitors;
		for (Tuple tuple : list) {

			protest = tuple.get(qAction.protest);
			visitors = tuple.get(qAction.count());

			result.put(protest, visitors);
		}

		return result;
	}

	public Map<Protest, Long> getClosedProtests() {

		QAction qAction = QAction.action;

		List<Tuple> list = new JPAQuery(entityManager)
				.from(qAction)
				.where(qAction.actionType.eq(ActionType.DEMONSTRATOR).and(
						isClosedProtest(qAction.protest)))
				.groupBy(qAction.protest.id, qAction.date)
				.orderBy(qAction.protest.start.desc()).limit(PAGE_LIMIT)
				.list(qAction.protest, qAction.value.sum());

		Map<Protest, Long> result = Maps.newHashMap();
		Protest protest;
		Long maxProtesters;
		Long protesters;
		for (Tuple tuple : list) {

			protest = tuple.get(qAction.protest);

			maxProtesters = result.get(protest);
			protesters = tuple.get(qAction.value.sum()).longValue();

			if (maxProtesters == null
					|| (maxProtesters != null && maxProtesters < protesters)) {
				result.put(protest, protesters);
			}

		}

		return result;
	}

	public Map<Protest, Long> getInProgressProtests() {

		QAction qAction = QAction.action;

		JPAQuery query = new JPAQuery(entityManager).from(qAction);
		List<Tuple> list = query
				.where(qAction.actionType.eq(ActionType.DEMONSTRATOR).and(
						isInProgressProtest(qAction.protest)))
				.groupBy(qAction.protest).orderBy(qAction.protest.name.desc())
				.limit(PAGE_LIMIT).list(qAction.protest, qAction.value.sum());

		Map<Protest, Long> result = Maps.newHashMap();
		Protest protest;
		Long protesters;
		for (Tuple tuple : list) {

			protest = tuple.get(qAction.protest);
			protesters = tuple.get(qAction.value.sum()).longValue();

			result.put(protest, protesters);
		}

		return result;
	}

}
