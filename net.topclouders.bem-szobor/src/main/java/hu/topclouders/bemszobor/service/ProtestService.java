package hu.topclouders.bemszobor.service;

import hu.topclouders.bemszobor.dao.IProtestRepository;
import hu.topclouders.bemszobor.domain.Protest;
import hu.topclouders.bemszobor.domain.QAction;
import hu.topclouders.bemszobor.domain.QProtest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.Predicate;

@Service
public class ProtestService {

	@Autowired
	private IProtestRepository demonstrationRepository;

	@PersistenceContext
	private EntityManager entityManager;

	public Map<Protest, Long> getActiveDemonstrations() {
		Date serverTime = Calendar.getInstance().getTime();

		QAction qAction = QAction.action;

		JPAQuery query = new JPAQuery(entityManager).from(qAction);

		List<Tuple> tuples = query
				.groupBy(qAction.protest)
				.where(qAction.protest.created.isNotNull().and(
						qAction.protest.start.before(serverTime).and(
								qAction.protest.created.before(serverTime).and(
										qAction.protest.closed.isNull()))))
				.list(qAction.protest, qAction.count());

		Map<Protest, Long> result = new HashMap<Protest, Long>();
		for (Tuple tuple : tuples) {
			Protest protest = tuple.get(0, Protest.class);
			Long visitors = tuple.get(1, Long.class);

			result.put(protest, visitors);
		}

		return result;
	}

	public List<Protest> getClosedProtests() {
		Date serverTime = Calendar.getInstance().getTime();

		QProtest qProtest = QProtest.protest;
		Predicate predicate = qProtest.closed.isNotNull().and(
				qProtest.closed.before(serverTime));

		return new ArrayList<Protest>();
	}

	public List<Protest> getInProgressProtests() {
		Date serverTime = Calendar.getInstance().getTime();

		QProtest qProtest = QProtest.protest;
		Predicate predicate = qProtest.start.before(serverTime).and(
				qProtest.end.isNull().or(qProtest.end.after(serverTime))
						.and(qProtest.closed.isNull()));

		return Lists.newArrayList(demonstrationRepository.findAll());
	}

}
