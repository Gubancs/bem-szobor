package hu.topclouders.bemszobor.controllers;

import hu.topclouders.bemszobor.service.GeoService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GeoStatisticsController {

	@Autowired
	private GeoService geoService;

	@RequestMapping(value = "/stat/chart/geo/countries/{protestId}/{type}", method = RequestMethod.GET)
	@ResponseBody
	public String getCountries(@PathVariable("protestId") Long protestId,
			@PathVariable("type") String type) throws IOException {

		return null;
	}

	@RequestMapping(value = "/stat/chart/geo/cities/{protestId}/{country}/{type}", method = RequestMethod.GET)
	@ResponseBody
	public String getCities(@PathVariable("protestId") Long protestId,
			@PathVariable("country") String countryCode,
			@PathVariable("type") String type) throws IOException {

		return null;
	}

}
