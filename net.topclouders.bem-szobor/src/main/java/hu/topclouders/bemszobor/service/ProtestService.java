package hu.topclouders.bemszobor.service;

import hu.topclouders.bemszobor.dao.IDemonstrationDao;
import hu.topclouders.bemszobor.domain.Demonstration;
import hu.topclouders.bemszobor.domain.QAction;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.mysema.query.Tuple;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ProtestService {

	@Autowired
	private IDemonstrationDao demonstrationRepository;

	public Map<Demonstration, Long> getActiveDemonstrations() {
		Date systemDate = Calendar.getInstance().getTime();

		QAction qAction = QAction.action;

		Map<Demonstration, Long> result = Maps.newHashMap();
		for (Tuple tuple : demonstrationRepository
				.getActiveDemonstrators(systemDate)) {
			result.put(tuple.get(qAction.demonstration),
					tuple.get(qAction.count()));
		}

		return result;
	}

	public Map<Demonstration, Long> getClosedDemonstrations() {
		Date systemDate = Calendar.getInstance().getTime();
		QAction qAction = QAction.action;

		Map<Demonstration, Long> result = Maps.newHashMap();
		Demonstration protest;
		Long maxProtesters;
		Long protesters;
		for (Tuple row : demonstrationRepository
				.getClosedDemonstrations(systemDate)) {

			protest = row.get(qAction.demonstration);

			maxProtesters = result.get(protest);
			protesters = row.get(qAction.value.sum()).longValue();

			if (maxProtesters == null
					|| (maxProtesters != null && maxProtesters < protesters)) {
				result.put(protest, protesters);
			}

		}

		return result;
	}

	public Map<Demonstration, Long> getInProgressDemonstrations() {

		Date systemDate = Calendar.getInstance().getTime();

		QAction qAction = QAction.action;

		Map<Demonstration, Long> result = Maps.newHashMap();
		for (Tuple tuple : demonstrationRepository
				.getInProgressProtests(systemDate)) {
			result.put(tuple.get(qAction.demonstration),
					tuple.get(qAction.value.sum()).longValue());
		}

		return result;
	}
}
