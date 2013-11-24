package hu.topclouders.bemszobor.json.mapper;

import hu.topclouders.bemszobor.enums.ActionType;

public class ActionTypeSerializer {

	public String serialize(ActionType actionType) {

		if (actionType == null) {
			return null;
		}

		switch (actionType) {
		case DEMONSTRATOR:
			return "D";
		case COUNTER_DEMONSTRATOR:
			return "CD";
		case VISITOR:
			return "V";
		default:
			return null;
		}
	}

	public ActionType deserialize(String type) {

		if (type == null || type.isEmpty()) {
			return null;
		}

		switch (type) {
		case "D":
			return ActionType.DEMONSTRATOR;
		case "d":
			return ActionType.DEMONSTRATOR;
		case "CD":
			return ActionType.COUNTER_DEMONSTRATOR;
		case "cd":
			return ActionType.COUNTER_DEMONSTRATOR;
		case "V":
			return ActionType.VISITOR;
		case "v":
			return ActionType.VISITOR;
		default:
			return null;
		}
	}
}
