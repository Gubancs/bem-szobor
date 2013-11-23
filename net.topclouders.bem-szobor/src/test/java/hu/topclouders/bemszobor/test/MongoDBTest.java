package hu.topclouders.bemszobor.test;

import hu.topclouders.bemszobor.domain.Demonstration;
import hu.topclouders.bemszobor.domain.Person;
import hu.topclouders.bemszobor.domain.Person.Gender;
import hu.topclouders.bemszobor.domain.Visitor;
import hu.topclouders.bemszobor.enums.ActionType;
import hu.topclouders.bemszobor.repositories.ActionRepository;
import hu.topclouders.bemszobor.repositories.DemonstrationRepository;
import hu.topclouders.bemszobor.repositories.VisitorRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@ContextConfiguration(locations = "/mongodb-context.xml")
public class MongoDBTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private VisitorRepository visitorRepository;

	@Autowired
	private DemonstrationRepository demonstrationRepository;

	@Autowired
	private ActionRepository actionRepository;

	private List<Demonstration> demonstrations = new ArrayList<Demonstration>();

	@BeforeClass
	public void beforeClass() {

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 3);

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -20);
		for (int i = 0; i < 100; i++) {

			demonstrations.add(createDemonstration("demonstration" + i,
					cal.getTime()));

			cal.add(Calendar.DATE, 1);
		}

	}

	private Demonstration createDemonstration(String name, Date start) {
		Demonstration demonstration = new Demonstration();
		demonstration.setEmail("ingatlanmarket.net@gmail.com");
		demonstration.setOrganizer("Istvan Benedek");
		demonstration.setStart(start);
		demonstration.setAddress("Békéscsaba");
		demonstration.setName(name);

		return demonstrationRepository.save(demonstration);
	}

	@Test
	public void mongoDbTest() {

		List<Demonstration> demonstrations = demonstrationRepository.findAll();

		for (Demonstration demonstration : demonstrations) {

			createVisitors(demonstration);
		}

	}

	private List<Visitor> createVisitors(Demonstration demonstration) {
		Visitor visitor;

		List<Visitor> visitors = new ArrayList<Visitor>();
		for (int i = 0; i < 1000; i++) {
			visitor = new Visitor();
			visitor.setActionType(ActionType.VISIT);
			visitor.setCity("City_" + i);
			visitor.setRegion("Region_" + i);
			visitor.setCountry("Country_" + i);
			visitor.setJoinDate(Calendar.getInstance().getTime());
			visitor.setPerson(new Person("Person_" + i, Gender.MALE, 25));
			visitor.setDemonstration(demonstrations.get(0));
			visitor.setUuid(UUID.randomUUID().toString());
			visitor.setDemonstration(demonstration);

			visitorRepository.save(visitor);

			visitors.add(visitor);
		}

		return visitors;
	}

	@AfterClass
	public void afterClass() {
	}
}
