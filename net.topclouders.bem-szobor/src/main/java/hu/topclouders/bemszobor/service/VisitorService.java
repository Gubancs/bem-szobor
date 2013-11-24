package hu.topclouders.bemszobor.service;

import hu.topclouders.bemszobor.dao.IDemonstrationDao;
import hu.topclouders.bemszobor.dao.IVisitorDao;
import hu.topclouders.bemszobor.domain.Demonstration;
import hu.topclouders.bemszobor.domain.Visitor;
import hu.topclouders.bemszobor.enums.ActionType;

import java.util.Calendar;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class VisitorService {

	@Autowired
	private IVisitorDao visitorDao;

	@Autowired
	private IDemonstrationDao demonstrationDao;

	@Autowired
	private ActionService actionService;

	@Autowired
	private LocationService locationService;

	public Visitor createVisitor(Long demonstrationId) {
		Assert.notNull(demonstrationId);

		Demonstration demonstration = demonstrationDao.findOne(demonstrationId);

		if (demonstration == null) {
			throw new IllegalArgumentException(
					"Demonstration cannot be found with id: " + demonstrationId);
		}

		Visitor visitor = new Visitor();
		visitor.setDemonstration(demonstration);
		visitor.setActionType(ActionType.VISITOR);
		visitor.setUuid(UUID.randomUUID().toString());
		visitor.setJoinDate(Calendar.getInstance().getTime());
		visitor.setActive(true);

		visitor = visitorDao.save(visitor);

		actionService.createAction(demonstration, visitor);

		return visitor;
	}

	public Visitor demonstrate(Visitor visitor) {
		Assert.notNull(visitor);

		visitor.setActionType(ActionType.DEMONSTRATOR);

		visitor = visitorDao.save(visitor);

		actionService.createAction(visitor.getDemonstration(), visitor);

		return visitor;
	}

	public Visitor counterDemonstrate(Visitor visitor) {
		Assert.notNull(visitor);

		visitor.setActionType(ActionType.COUNTER_DEMONSTRATOR);

		visitor = visitorDao.save(visitor);

		actionService.createAction(visitor.getDemonstration(), visitor);

		return visitor;
	}

}
