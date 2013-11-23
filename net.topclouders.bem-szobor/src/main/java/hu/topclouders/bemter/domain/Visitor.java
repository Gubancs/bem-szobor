package hu.topclouders.bemter.domain;

import hu.topclouders.bemter.enums.ActionType;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Visitor extends MongoDocument {

	private Demonstration demonstration;
	
	private String uuid;

	private Person person;

	private Date joinDate;

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

	public Demonstration getDemonstration() {
		return demonstration;
	}

	public void setDemonstration(Demonstration demonstration) {
		this.demonstration = demonstration;
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
