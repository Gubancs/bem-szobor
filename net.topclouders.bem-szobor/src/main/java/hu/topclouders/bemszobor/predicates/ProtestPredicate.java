package hu.topclouders.bemszobor.predicates;

import hu.topclouders.bemszobor.domain.QDemonstration;

import java.util.Date;

import com.mysema.query.types.Predicate;

public class ProtestPredicate {

	public static Predicate isClosedDemonstration(QDemonstration qProtest, Date date) {
		return qProtest.closed.isNotNull().and(qProtest.closed.before(date));
	}

	public static Predicate isInProgressDemonstration(QDemonstration qProtest,
			Date date) {
		return qProtest.start.before(date)
				.and(qProtest.end.isNull().or(qProtest.end.after(date)))
				.and(qProtest.closed.isNull());
	}

	public static Predicate isActiveDemonstration(QDemonstration qProtest, Date date) {

		return qProtest.created.isNotNull().and(
				qProtest.start.before(date).and(
						qProtest.created.before(date).and(
								qProtest.closed.isNull())));
	}
}
