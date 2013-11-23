package hu.topclouders.bemszobor.service;

import hu.topclouders.bemszobor.dao.IVisitorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitorService {

	@Autowired
	private IVisitorRepository visitorRepository;

}
