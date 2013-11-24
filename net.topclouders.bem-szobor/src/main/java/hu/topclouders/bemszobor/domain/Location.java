package hu.topclouders.bemszobor.domain;

import javax.persistence.Entity;

@Entity
public class Location extends AbstractEntity {
	private static final long serialVersionUID = -6456274410947235242L;

	private double latitude;

	private double longitude;

	private String country;

	private String region;

	private String city;

	public Location() {
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
