package hu.topclouders.bemszobor.service;

import hu.topclouders.bemszobor.dao.IActionDao;
import hu.topclouders.bemszobor.domain.Action;
import hu.topclouders.bemszobor.domain.Demonstration;
import hu.topclouders.bemszobor.domain.Visitor;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ActionService {

	@Autowired
	private IActionDao actionRepository;

	public void createAction(Demonstration demonstration, Visitor visitor) {
		Assert.notNull(demonstration);
		Assert.notNull(visitor);

		Action action = new Action(visitor);
		action.setDemonstration(demonstration);
		action.setDate(Calendar.getInstance().getTimeInMillis());
		action.setValue(1);

		actionRepository.save(action);

	}

}
