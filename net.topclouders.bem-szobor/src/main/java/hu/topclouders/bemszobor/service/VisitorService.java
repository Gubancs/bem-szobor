package hu.topclouders.bemszobor.service;

import hu.topclouders.bemszobor.dao.IDemonstrationDao;
import hu.topclouders.bemszobor.dao.IVisitorDao;
import hu.topclouders.bemszobor.domain.Demonstration;
import hu.topclouders.bemszobor.domain.Visitor;
import hu.topclouders.bemszobor.enums.ActionType;

import java.util.Calendar;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class VisitorService {

	private static final Logger LOGGER = Logger.getLogger(VisitorService.class);

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
			LOGGER.error("Demonstration cannot be found");
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

		LOGGER.info(String.format("New visitor created on %s demonstration",
				demonstration.getName()));

		actionService.createPlusAction(demonstration, visitor);

		return visitor;
	}

	public Visitor registerDemonstrator(Visitor visitor) {
		Assert.notNull(visitor);

		visitor = updateVisitor(visitor, visitor.getDemonstration().getId(),
				ActionType.DEMONSTRATOR);

		LOGGER.info(String.format(
				"Demonstrator %s registered in the %s demonstration",
				visitor.getId(), visitor.getDemonstration().getName()));

		return visitor;
	}

	public Visitor registerCounterDemonstrator(Visitor visitor) {
		Assert.notNull(visitor);

		visitor = updateVisitor(visitor, visitor.getDemonstration().getId(),
				ActionType.COUNTER_DEMONSTRATOR);

		LOGGER.info(String.format(
				"Counter Demonstrator %s registered in the %s demonstration",
				visitor.getId(), visitor.getDemonstration().getName()));

		return visitor;
	}

	public Visitor registerVisitor(Visitor visitor, Long demonstrationId) {
		return updateVisitor(visitor, demonstrationId, ActionType.VISITOR);
	}

	public Visitor updateVisitor(Visitor visitor, Long demonstrationId,
			ActionType actionType) {
		Assert.notNull(demonstrationId);
		visitor = visitorDao.findOne(visitor.getId());

		actionService.createMinusAction(visitor.getDemonstration(), visitor);

		Demonstration demonstration = demonstrationDao.findOne(demonstrationId);
		if (demonstration == null) {
			throw new IllegalArgumentException(
					"Demonstration cannot be found with id: " + demonstrationId);
		}

		visitor.setDemonstration(demonstration);
		visitor.setActionType(actionType);

		visitor = visitorDao.save(visitor);

		actionService.createPlusAction(demonstration, visitor);

		return visitorDao.save(visitor);
	}

}
