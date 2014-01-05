package hu.topclouders.bemszobor.domain;

import hu.topclouders.bemszobor.enums.ActionType;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.springframework.util.Assert;

@Entity
public class Action extends AbstractEntity {

	private static final long serialVersionUID = -5820373980960232740L;

	public static enum ActionValue {
		DO, UNDO;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Demonstration demonstration;

	@ManyToOne(fetch = FetchType.LAZY)
	private Visitor visitor;

	private long date;

	private int value = 1; // JOIN +1; LEAVE -1

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
	
	public void setActionValue(ActionValue actionValue) {
		if (actionValue == null) {
			throw new NullPointerException();
		}
		
		if (actionValue == ActionValue.DO) {
			this.value = 1;
		}else {
			this.value = -1;
		}
	}

	public ActionType getActionType() {
		return actionType;
	}

	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}

}
