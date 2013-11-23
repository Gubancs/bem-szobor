package hu.topclouders.bemszobor.services;

import hu.topclouders.bemszobor.domain.QVisitor;
import hu.topclouders.bemszobor.domain.Visitor;
import hu.topclouders.bemszobor.repositories.VisitorRepository;

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
	private VisitorRepository visitorRepository;

	@RequestMapping(value = "/demonstrators/${demonstration}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Integer> getDemonstratorsGlobal(
			@RequestParam(value = "demonstration", required = true) String demonstration) {

		QVisitor qVisitor = QVisitor.visitor;
		Predicate predicate = qVisitor.demonstration.name.eq(demonstration);

		Map<String, Integer> result = new HashMap<String, Integer>();
		Integer counter;
		for (Visitor visitor : visitorRepository.findAll(predicate)) {

			counter = result.get(visitor.getCountry());

			if (counter == null) {
				result.put(visitor.getCountry(), 1);
			} else {
				result.put(visitor.getCountry(), counter++);
			}
		}

		return result;
	}

	@RequestMapping(value = "/demonstrators/${demonstration}/${country}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Integer> getDemonstrators(
			@RequestParam(value = "demonstration", required = true) String demonstration,
			@RequestParam(value = "country", required = true) String country) {

		QVisitor qVisitor = QVisitor.visitor;
		Predicate predicate = qVisitor.demonstration.name.eq(demonstration)
				.and(qVisitor.country.eq(country));

		Map<String, Integer> result = new HashMap<String, Integer>();

		Integer counter;
		for (Visitor visitor : visitorRepository.findAll(predicate)) {

			counter = result.get(visitor.getCity());
			counter = counter == null ? 1 : counter++;

			result.put(visitor.getCountry(), counter);
		}

		return result;
	}
}
