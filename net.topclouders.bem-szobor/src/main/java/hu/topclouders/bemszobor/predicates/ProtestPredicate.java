package hu.topclouders.bemszobor.predicates;

import hu.topclouders.bemszobor.domain.QDemonstration;

import java.util.Date;

import com.mysema.query.types.Predicate;

public class ProtestPredicate {

	public static Predicate isClosedProtest(QDemonstration qProtest, Date date) {
		return qProtest.closed.isNotNull().and(qProtest.closed.before(date));
	}

	public static Predicate isInProgressProtest(QDemonstration qProtest,
			Date date) {
		return qProtest.start.before(date)
				.and(qProtest.end.isNull().or(qProtest.end.after(date)))
				.and(qProtest.closed.isNull());
	}

	public static Predicate isActiveProtest(QDemonstration qProtest, Date date) {

		return qProtest.created.isNotNull().and(
				qProtest.start.before(date).and(
						qProtest.created.before(date).and(
								qProtest.closed.isNull())));
	}
}
