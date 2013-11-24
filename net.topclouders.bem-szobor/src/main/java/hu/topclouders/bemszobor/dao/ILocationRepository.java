package hu.topclouders.bemszobor.dao;

import hu.topclouders.bemszobor.domain.Location;

public interface ILocationRepository extends IGenericDao<Location> {

	public Location findLocationByCountryAndCity(String country, String city);

}
