package hu.topclouders.bemszobor.enums;

public enum ActionType {
	DEMONSTRATOR, COUNTER_DEMONSTRATOR, VISITOR, SIGN_UP;

	public boolean isDemonstrator() {
		return this.equals(DEMONSTRATOR) || this.equals(COUNTER_DEMONSTRATOR);
	}
}
