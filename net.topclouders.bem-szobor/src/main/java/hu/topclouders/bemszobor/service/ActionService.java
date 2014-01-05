package hu.topclouders.bemszobor.service;

import hu.topclouders.bemszobor.dao.IActionDao;
import hu.topclouders.bemszobor.domain.Action;
import hu.topclouders.bemszobor.domain.Action.ActionValue;
import hu.topclouders.bemszobor.domain.Demonstration;
import hu.topclouders.bemszobor.domain.Visitor;
import hu.topclouders.bemszobor.enums.ActionType;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ActionService {

	private static final Logger LOGGER = Logger.getLogger(ActionService.class);

	@Autowired
	private IActionDao actionRepository;

	public Action createPlusAction(Demonstration demonstration, Visitor visitor, ActionType actionType) {
		Assert.notNull(demonstration);
		Assert.notNull(visitor);
		Assert.notNull(actionType);

		Action action = new Action(visitor);
		action.setDemonstration(demonstration);
		action.setDate(Calendar.getInstance().getTimeInMillis());
		action.setActionValue(ActionValue.DO);
		action.setActionType(actionType);

		actionRepository.save(action);

		LOGGER.debug(String.format(
				"%s. Visitor %s action successfully created in the %s",
				visitor.getId(), action.getActionType(),
				demonstration.getName()));

		return action;
	}

	public void createMinusAction(Demonstration demonstration, Visitor visitor, ActionType actionType) {
		Assert.notNull(demonstration);
		Assert.notNull(visitor);
		Assert.notNull(actionType);

		Action action = new Action(visitor);
		action.setDemonstration(demonstration);
		action.setDate(Calendar.getInstance().getTimeInMillis());
		action.setActionValue(ActionValue.UNDO);
		action.setActionType(actionType);

		LOGGER.debug(String.format(
				"%s. Visitor %s action successfully invalidated in the %s",
				visitor.getId(), action.getActionType(),
				demonstration.getName()));

		actionRepository.save(action);

	}

}
