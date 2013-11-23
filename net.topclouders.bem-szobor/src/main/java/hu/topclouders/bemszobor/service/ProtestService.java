package hu.topclouders.bemszobor.service;

import hu.topclouders.bemszobor.dao.IProtestRepository;
import hu.topclouders.bemszobor.dao.IVisitorRepository;
import hu.topclouders.bemszobor.domain.Protest;
import hu.topclouders.bemszobor.domain.QProtest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.mysema.query.jpa.JPASubQuery;
import com.mysema.query.types.Predicate;

@Service
public class ProtestService {

	@Autowired
	private IProtestRepository demonstrationRepository;

	@Autowired
	private IVisitorRepository visitorRepository;

	public List<Protest> getActiveDemonstrations() {
		Date serverTime = Calendar.getInstance().getTime();

		QProtest qProtest = QProtest.protest;
		Predicate predicate = qProtest.created.isNotNull()
				.and(qProtest.created.before(serverTime))
				.and(qProtest.start.before(serverTime))
				.and(qProtest.closed.isNull());

		

		return Lists.newArrayList();
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
