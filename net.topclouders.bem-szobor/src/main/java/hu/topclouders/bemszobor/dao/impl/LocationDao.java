package hu.topclouders.bemszobor.dao.impl;

import hu.topclouders.bemszobor.dao.ILocationRepository;
import hu.topclouders.bemszobor.domain.Location;
import hu.topclouders.bemszobor.domain.QLocation;

import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.impl.JPAQuery;

@Repository
public class LocationDao extends AbstractJpaDao<Location> implements
		ILocationRepository {

	@Override
	public Location findLocationByCountryAndCity(String country, String city) {
		QLocation qLocation = QLocation.location;

		JPAQuery query = new JPAQuery(getEntityManager());
		query.from(qLocation).where(
				qLocation.country.equalsIgnoreCase(country).and(
						qLocation.city.equalsIgnoreCase(city)));
		return query.singleResult(qLocation);
	}

}
