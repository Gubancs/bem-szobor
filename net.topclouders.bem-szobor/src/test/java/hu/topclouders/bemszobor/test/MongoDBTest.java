package hu.topclouders.bemszobor.test;

import hu.topclouders.bemszobor.dao.IActionRepository;
import hu.topclouders.bemszobor.dao.ILocationRepository;
import hu.topclouders.bemszobor.dao.IProtestRepository;
import hu.topclouders.bemszobor.domain.Action;
import hu.topclouders.bemszobor.domain.Location;
import hu.topclouders.bemszobor.domain.Person;
import hu.topclouders.bemszobor.domain.Person.Gender;
import hu.topclouders.bemszobor.domain.Protest;
import hu.topclouders.bemszobor.domain.Visitor;
import hu.topclouders.bemszobor.enums.ActionType;
import hu.topclouders.bemszobor.service.ProtestService;
import hu.topclouders.bemszobor.service.RegistrationService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@ContextConfiguration(locations = "/service-context.xml")
public class MongoDBTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private IProtestRepository protestRepository;

	@Autowired
	private ILocationRepository locationDao;

	@Autowired
	private IActionRepository actionRepository;

	@Autowired
	private ProtestService protestService;

	private List<Protest> protests = new ArrayList<Protest>();

	@BeforeClass
	public void beforeClass() {

		Map<Protest, Long> activeDemonstrations = protestService
				.getActiveDemonstrators();

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 3);

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -4);
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

	private List<Visitor> createVisitors(Protest protest) {

		Person person;
		List<Visitor> visitors = new ArrayList<Visitor>();
		Location location = new Location();
		for (int i = 0; i < 10; i++) {

			person = new Person("Person_" + i, i % 3 == 0 ? Gender.MALE
					: Gender.FEMALE, 25);

			location = new Location();
			location.setCity("City_" + i % 2);
			location.setRegion("Region_" + i % 2);
			location.setCountry("Country_" + i % 2);
			location.setLatitude(Math.random() * 10 + 1);
			location.setLongitude(Math.random() * 10 + 1);

			location = locationDao.save(location);

			Visitor visitor = registrationService.registerVisitor(protest
					.getId(), i % 2 == 0 ? ActionType.DEMONSTRATOR
					: ActionType.COUNTER_DEMONSTRATOR, person, location);

			Action action = new Action(visitor, ActionType.JOIN);
			action.setDate(Calendar.getInstance().getTimeInMillis());
			action.setProtest(protest);
			action.setValue(1);

			actionRepository.save(action);

			action = new Action(visitor, ActionType.DEMONSTRATOR);
			action.setDate(Calendar.getInstance().getTimeInMillis());
			action.setProtest(protest);
			action.setValue(1);

			actionRepository.save(action);

			action = new Action(visitor, ActionType.DEMONSTRATOR);
			action.setDate(Calendar.getInstance().getTimeInMillis());
			action.setProtest(protest);
			action.setValue(-1);

			actionRepository.save(action);

			visitors.add(visitor);
		}

		return visitors;
	}

	@AfterClass
	public void afterClass() {

		int index;
		for (Protest protest : protests) {
			index = protests.indexOf(protest);

			if (index % 2 == 0) {
				protest.setEnd(Calendar.getInstance().getTime());
			}

			if (index % 2 == 1) {
				protest.setEnd(Calendar.getInstance().getTime());
				protest.setClosed(Calendar.getInstance().getTime());
			}

			protestRepository.update(protest);
		}
	}
}
