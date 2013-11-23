package hu.topclouders.bemszobor.test;

import hu.topclouders.bemszobor.domain.Action;
import hu.topclouders.bemszobor.domain.Demonstration;
import hu.topclouders.bemszobor.domain.Person;
import hu.topclouders.bemszobor.domain.Person.Gender;
import hu.topclouders.bemszobor.domain.Visitor;
import hu.topclouders.bemszobor.enums.ActionType;
import hu.topclouders.bemszobor.repositories.ActionRepository;
import hu.topclouders.bemszobor.repositories.DemonstrationRepository;
import hu.topclouders.bemszobor.repositories.VisitorRepository;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@ContextConfiguration(locations = "/mongodb-context.xml")
public class MongoDBTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private VisitorRepository visitorRepository;

	@Autowired
	private DemonstrationRepository demonstrationRepository;
	
	@Autowired
	private ActionRepository actionRepository;

	@BeforeClass
	public void beforeClass() {

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 3);

		Demonstration demonstration = new Demonstration();
		demonstration.setEmail("ingatlanmarket.net@gmail.com");
		demonstration.setOrganizer("Istvan Benedek");
		demonstration.setStart(calendar.getTime());
		demonstration.setAddress("Békéscsaba");

		demonstrationRepository.save(demonstration);

	}

	@BeforeMethod
	public void beforeMethod() {

	}

	@Test
	public void mongoDbTest() {

		List<Demonstration> demonstrations = demonstrationRepository.findAll();

		Visitor visitor = new Visitor();
		visitor.setActionType(ActionType.VISIT);
		visitor.setCity("Komlo");
		visitor.setRegion("Baranya");
		visitor.setCountry("Magyarorszag");
		visitor.setJoinDate(Calendar.getInstance().getTime());
		visitor.setPerson(new Person("Gubancs", Gender.MALE, 25));
		visitor.setDemonstration(demonstrations.get(0));
		visitor.setUuid(UUID.randomUUID().toString());

		visitorRepository.save(visitor);

		Action action = new Action(visitor, ActionType.DEMONSTRATOR);
		action.setDate(Calendar.getInstance().getTimeInMillis());
		action.setDemonstration(visitor.getDemonstration());
		action.setValue(1);
		
		actionRepository.save(action);

		visitor.setActionType(action.getActionType());
		
		visitorRepository.save(visitor);
	}

	@AfterClass
	public void afterClass() {
	}
}
