package hu.topclouders.bemszobor.controllers;

import hu.topclouders.bemszobor.domain.Protest;
import hu.topclouders.bemszobor.service.ProtestService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProtestController {

	@Autowired
	private ProtestService protestService;

	@RequestMapping(value = "/protests/active", method = RequestMethod.GET)
	@ResponseBody
	public List<Protest> getActiveProtests() {
		List<Protest> protests = protestService.getActiveDemonstrations();

		return protests;
	}

	@RequestMapping(value = "/protests/closed", method = RequestMethod.GET)
	@ResponseBody
	public List<Protest> getClosedProtests() {

		List<Protest> protests = protestService.getClosedProtests();

		return protests;
	}

	@RequestMapping(value = "/protests/inprogress", method = RequestMethod.GET)
	@ResponseBody
	public List<Protest> getInProgessProtests() {

		List<Protest> protests = protestService.getInProgressProtests();

		return protests;

	}
}
