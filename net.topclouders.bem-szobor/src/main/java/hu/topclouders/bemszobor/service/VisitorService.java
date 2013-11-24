package hu.topclouders.bemszobor.service;

import hu.topclouders.bemszobor.dao.IProtestRepository;
import hu.topclouders.bemszobor.dao.IVisitorRepository;
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
	private IVisitorRepository visitorRepository;

	@Autowired
	private IProtestRepository protestRepository;

	@Autowired
	private ActionService actionService;

	@Autowired
	private LocationService locationService;

	public Visitor createVisitor(Long demonstrationId) {
		Assert.notNull(demonstrationId);

		Demonstration protest = protestRepository.findOne(demonstrationId);

		if (protest == null) {
			throw new IllegalArgumentException(
					"Demonstration cannot be found with id: " + demonstrationId);
		}

		Visitor visitor = new Visitor();
		visitor.setProtest(protest);
		visitor.setActionType(ActionType.VISITOR);
		visitor.setUuid(UUID.randomUUID().toString());
		visitor.setJoinDate(Calendar.getInstance().getTime());
		visitor.setActive(true);

		visitorRepository.save(visitor);

		actionService.createAction(protest, visitor);

		return visitor;
	}

}
