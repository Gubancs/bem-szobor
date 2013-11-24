package hu.topclouders.bemszobor.enums;

public enum ActionType {
	DEMONSTRATOR, COUNTER_DEMONSTRATOR, VISITOR, JOIN;

	public boolean isDemonstrator() {
		return this.equals(DEMONSTRATOR) || this.equals(COUNTER_DEMONSTRATOR);
	}
}
