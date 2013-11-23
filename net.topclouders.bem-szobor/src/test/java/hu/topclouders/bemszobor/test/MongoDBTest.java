package hu.topclouders.bemszobor.test;

import hu.topclouders.bemszobor.dao.IActionRepository;
import hu.topclouders.bemszobor.dao.IProtestRepository;
import hu.topclouders.bemszobor.dao.IVisitorRepository;
import hu.topclouders.bemszobor.domain.Action;
import hu.topclouders.bemszobor.domain.Person;
import hu.topclouders.bemszobor.domain.Person.Gender;
import hu.topclouders.bemszobor.domain.Protest;
import hu.topclouders.bemszobor.domain.Visitor;
import hu.topclouders.bemszobor.enums.ActionType;
import hu.topclouders.bemszobor.service.ProtestService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@ContextConfiguration(locations = "/service-context.xml")
public class MongoDBTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private IVisitorRepository visitorRepository;

	@Autowired
	private IProtestRepository protestRepository;

	@Autowired
	private IActionRepository actionRepository;

	@Autowired
	private ProtestService protestService;

	private List<Protest> protests = new ArrayList<Protest>();

	@BeforeClass
	public void beforeClass() {

		Map<Protest, Long> activeDemonstrations = protestService
				.getActiveDemonstrations();

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 3);

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -20);
		for (int i = 0; i < 10; i++) {

			protests.add(createProtest("protest" + i, cal.getTime()));

			cal.add(Calendar.DATE, 1);
		}

	}

	private Protest createProtest(String name, Date start) {
		Protest protest = new Protest();
		protest.setEmail("ingatlanmarket.net@gmail.com");
		protest.setOrganizer("Istvan Benedek");
		protest.setStart(start);
		protest.setAddress("Békéscsaba");
		protest.setName(name);
		protest.setCreated(start);

		return protestRepository.save(protest);
	}

	@Test
	public void mongoDbTest() {

		List<Protest> demonstrations = protestRepository.findAll();

		for (Protest demonstration : demonstrations) {

			createVisitors(demonstration);
		}

	}

	private List<Visitor> createVisitors(Protest demonstration) {
		Visitor visitor;

		List<Visitor> visitors = new ArrayList<Visitor>();
		for (int i = 0; i < 100; i++) {
			visitor = new Visitor();
			visitor.setActionType(ActionType.VISIT);
			visitor.setCity("City_" + i);
			visitor.setRegion("Region_" + i);
			visitor.setCountry("Country_" + i);
			visitor.setJoinDate(Calendar.getInstance().getTime());
			visitor.setPerson(new Person("Person_" + i, Gender.MALE, 25));
			visitor.setProtest(protests.get(0));
			visitor.setUuid(UUID.randomUUID().toString());
			visitor.setProtest(demonstration);

			visitorRepository.save(visitor);

			Action action = new Action(visitor, ActionType.JOIN);
			action.setDate(Calendar.getInstance().getTimeInMillis());
			action.setProtest(demonstration);
			action.setValue(1);

			actionRepository.save(action);

			visitors.add(visitor);
		}

		return visitors;
	}

	@AfterClass
	public void afterClass() {

	}
}
