package hu.topclouders.bemszobor.domain;

import hu.topclouders.bemszobor.enums.ActionType;

import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Entity
public class Visitor extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8288261956764742517L;

	@ManyToOne
	private Protest protest;

	private String uuid;

	@Embedded
	private Person person;

	private Date joinDate;

	@Enumerated
	private ActionType actionType;

	private boolean active = true;

	private double latitude;

	private double longitude;

	private String country;

	private String region;

	private String city;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Protest getProtest() {
		return protest;
	}

	public void setProtest(Protest protest) {
		this.protest = protest;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public ActionType getActionType() {
		return actionType;
	}

	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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
