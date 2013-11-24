package hu.topclouders.bemszobor.service;

import hu.topclouders.bemszobor.dao.ILocationRepository;
import hu.topclouders.bemszobor.dao.IProtestRepository;
import hu.topclouders.bemszobor.dao.IVisitorRepository;
import hu.topclouders.bemszobor.domain.Location;
import hu.topclouders.bemszobor.domain.Person;
import hu.topclouders.bemszobor.domain.Protest;
import hu.topclouders.bemszobor.domain.Visitor;
import hu.topclouders.bemszobor.enums.ActionType;

import java.util.Calendar;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class RegistrationService {

	@Autowired
	private IVisitorRepository visitorRepository;

	@Autowired
	private IProtestRepository protestRepository;

	@Autowired
	private ILocationRepository locationRepository;

	public Visitor registerVisitor(Long protestId, ActionType actionType,
			Person person, Location location) {
		Assert.notNull(protestId);
		Assert.notNull(actionType);
		Assert.notNull(person);

		location = getOrCreateLocation(location);

		Protest protest = protestRepository.findOne(protestId);

		if (protest == null) {
			throw new IllegalArgumentException("Protest not found");
		}

		Visitor visitor = new Visitor();
		visitor.setActionType(actionType);
		visitor.setJoinDate(Calendar.getInstance().getTime());
		visitor.setPerson(person);
		visitor.setProtest(protest);
		visitor.setUuid(UUID.randomUUID().toString());
		visitor.setActive(true);
		visitor.setLocation(location);

		return visitorRepository.save(visitor);
	}

	private Location getOrCreateLocation(Location location) {

		Assert.notNull(location.getCountry());
		Assert.notNull(location.getCity());

		Location existingLocation = locationRepository
				.findLocationByCountryAndCity(location.getCountry(),
						location.getCity());

		if (existingLocation == null) {

			existingLocation = locationRepository.save(location);
		}

		return existingLocation;
	}
}
