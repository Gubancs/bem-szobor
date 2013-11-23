package hu.topclouders.bemter.test;

import hu.topclouders.bemter.domain.Demonstration;
import hu.topclouders.bemter.repositories.DemonstrationRepository;
import hu.topclouders.bemter.repositories.VisitorRepository;

import java.util.Calendar;

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
	private VisitorRepository personRepository;

	@Autowired
	private DemonstrationRepository demonstrationRepository;

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

	}

	@AfterClass
	public void afterClass() {
	}
}
