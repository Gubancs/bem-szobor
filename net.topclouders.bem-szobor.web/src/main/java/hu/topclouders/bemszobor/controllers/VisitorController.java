package hu.topclouders.bemszobor.controllers;

import hu.topclouders.bemszobor.dao.IVisitorRepository;
import hu.topclouders.bemszobor.domain.QVisitor;
import hu.topclouders.bemszobor.domain.Visitor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysema.query.types.Predicate;

@Controller
public class VisitorController {

	@Autowired
	private IVisitorRepository visitorRepository;

	@RequestMapping(value = "/protests/{protest}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Integer> getDemonstratorsGlobal(
			@RequestParam(value = "demonstration", required = true) String protest) {

		QVisitor qVisitor = QVisitor.visitor;
		Predicate predicate = qVisitor.protest.name.eq(protest);

		Map<String, Integer> result = new HashMap<String, Integer>();
		Integer counter;
		for (Visitor visitor : visitorRepository.findAll()) {

			counter = result.get(visitor.getCountry());

			if (counter == null) {
				result.put(visitor.getCountry(), 1);
			} else {
				result.put(visitor.getCountry(), counter++);
			}
		}

		return result;
	}

	@RequestMapping(value = "/protests/{protest}/{country}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Integer> getDemonstrators(
			@RequestParam(value = "protest", required = true) String protest,
			@RequestParam(value = "country", required = true) String country) {

		QVisitor qVisitor = QVisitor.visitor;
		Predicate predicate = qVisitor.protest.name.eq(protest).and(
				qVisitor.country.eq(country));

		Map<String, Integer> result = new HashMap<String, Integer>();

		Integer counter;
		for (Visitor visitor : visitorRepository.findAll()) {

			counter = result.get(visitor.getCity());
			counter = counter == null ? 1 : counter++;

			result.put(visitor.getCountry(), counter);
		}

		return result;
	}
}
