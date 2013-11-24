package hu.topclouders.bemszobor.controllers;

import hu.topclouders.bemszobor.domain.Location;
import hu.topclouders.bemszobor.domain.Person;
import hu.topclouders.bemszobor.domain.Visitor;
import hu.topclouders.bemszobor.enums.ActionType;
import hu.topclouders.bemszobor.json.mapper.ActionTypeSerializer;
import hu.topclouders.bemszobor.service.RegistrationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class RegistrationController {

	@Autowired
	private RegistrationService registrationService;

	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	@RequestMapping(value = "/registration/{protestId}/{actionType}", method = RequestMethod.POST)
	public void registerVisitor(@PathVariable("protestId") Long protestId,
			@PathVariable("actionType") String type, @RequestBody Person person) {
		ActionType actionType = new ActionTypeSerializer().deserialize(type);

		Location location= new Location();
		location.setCountry("HUN");
		location.setCity("Budapest");
		Visitor visitor = registrationService.registerVisitor(protestId,
				actionType, person, location);

		System.out.println("Registration success: "
				+ visitor.getPerson().getNick());

	}
}
