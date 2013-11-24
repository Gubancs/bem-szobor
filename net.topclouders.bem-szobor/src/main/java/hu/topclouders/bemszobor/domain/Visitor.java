package hu.topclouders.bemszobor.domain;

import hu.topclouders.bemszobor.enums.ActionType;

import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Entity
public class Visitor extends AbstractEntity {

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

	@ManyToOne
	private Location location;

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

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}
