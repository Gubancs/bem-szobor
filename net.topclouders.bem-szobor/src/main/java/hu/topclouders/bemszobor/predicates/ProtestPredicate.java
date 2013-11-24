package hu.topclouders.bemszobor.predicates;

import hu.topclouders.bemszobor.domain.QProtest;

import java.util.Calendar;
import java.util.Date;

import com.mysema.query.types.Predicate;

public class ProtestPredicate {
	public static Predicate isClosedProtest(QProtest qProtest) {
		return qProtest.closed.isNotNull();
	}

	public static Predicate isInProgressProtest(QProtest qProtest) {
		Date date = Calendar.getInstance().getTime();
		return qProtest.start.before(date)
				.and(qProtest.end.isNull().or(qProtest.end.after(date)))
				.and(qProtest.closed.isNull());
	}

	public static Predicate isActiveProtest(QProtest qProtest) {

		Date date = Calendar.getInstance().getTime();

		return qProtest.created.isNotNull().and(
				qProtest.start.before(date).and(
						qProtest.created.before(date).and(
								qProtest.closed.isNull())));
	}
}
