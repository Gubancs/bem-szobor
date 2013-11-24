package hu.topclouders.bemszobor.service;

import hu.topclouders.bemszobor.dao.ILocationDao;
import hu.topclouders.bemszobor.domain.Location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class LocationService {

	@Autowired
	private ILocationDao locationRepository;

	public Location getOrCreateLocation(String countryCode, String city) {

		Assert.notNull(countryCode);
		Assert.notNull(city);

		Location location = locationRepository
				.findLocationByCountryAndCity(countryCode, city);

		if (location == null) {
			location = new Location();
			location.setCountry(countryCode);
			location.setCity(city);
			
			location = locationRepository.save(location);
		}

		return location;
	}
}
