package hu.topclouders.bemszobor.domain;

import hu.topclouders.bemszobor.enums.ActionType;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import org.springframework.util.Assert;

@Entity
public class Action extends AbstractEntity {

	private static final long serialVersionUID = -5820373980960232740L;

	@ManyToOne
	private Demonstration demonstration;

	@ManyToOne
	private Visitor visitor;

	private long date;

	private int value;

	@Enumerated
	private ActionType actionType;

	public Action() {
	}

	public Action(Visitor visitor) {
		Assert.notNull(visitor);
		Assert.notNull(visitor.getActionType());

		this.visitor = visitor;
		this.actionType = visitor.getActionType();
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
