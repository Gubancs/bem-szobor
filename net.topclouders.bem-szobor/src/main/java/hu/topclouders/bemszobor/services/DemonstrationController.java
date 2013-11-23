package hu.topclouders.bemszobor.services;

import hu.topclouders.bemszobor.domain.Demonstration;
import hu.topclouders.bemszobor.domain.QDemonstration;
import hu.topclouders.bemszobor.repositories.DemonstrationRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.common.collect.Lists;
import com.mysema.query.types.Predicate;

@Controller
public class DemonstrationController {

	@Autowired
	private DemonstrationRepository demonstrationRepository;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Demonstration> createDemonstration(
			@RequestBody Demonstration demonstration,
			UriComponentsBuilder builder) {

		return null;
	}

	@RequestMapping(value = "/demonstrations/active", method = RequestMethod.GET)
	@ResponseBody
	public List<Demonstration> getActiveDemonstrations() {
		Date serverTime = Calendar.getInstance().getTime();

		QDemonstration qDemonstration = QDemonstration.demonstration;
		Predicate predicate = qDemonstration.created.isNotNull()
				.and(qDemonstration.created.before(serverTime))
				.and(qDemonstration.start.after(serverTime))
				.and(qDemonstration.closed.isNull());

		return Lists.newArrayList(demonstrationRepository.findAll(predicate));
	}

	@RequestMapping(value = "/demonstrations/closed", method = RequestMethod.GET)
	@ResponseBody
	public List<Demonstration> getClosedDemonstrations() {
		Date serverTime = Calendar.getInstance().getTime();

		QDemonstration qDemonstration = QDemonstration.demonstration;
		Predicate predicate = qDemonstration.closed.isNotNull().and(
				qDemonstration.closed.before(serverTime));

		return Lists.newArrayList(demonstrationRepository.findAll(predicate));
	}

	@RequestMapping(value = "/demonstraitons/inprogress", method = RequestMethod.GET)
	@ResponseBody
	public List<Demonstration> getInProgessDemonstrations() {
		Date serverTime = Calendar.getInstance().getTime();

		QDemonstration qDemonstration = QDemonstration.demonstration;
		Predicate predicate = qDemonstration.start.before(serverTime).and(
				qDemonstration.end.isNull()
						.or(qDemonstration.end.after(serverTime))
						.and(qDemonstration.closed.isNull()));

		return Lists.newArrayList(demonstrationRepository.findAll(predicate));

	}
}
