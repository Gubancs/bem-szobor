package hu.topclouders.bemszobor.domain;

import hu.topclouders.bemszobor.enums.ActionType;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Action extends MongoDocument {

	private Demonstration demonstration;

	private Visitor visitor;

	private long date;

	private int value;

	private ActionType actionType;

	public Action(Visitor visitor, ActionType actionType) {
		this.visitor = visitor;
		this.actionType = actionType;
	}

	public Demonstration getDemonstration() {
		return demonstration;
	}

	public void setDemonstration(Demonstration demonstration) {
		this.demonstration = demonstration;
	}

	public Visitor getVisitor() {
		return visitor;
	}

	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public ActionType getActionType() {
		return actionType;
	}

	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}

}
