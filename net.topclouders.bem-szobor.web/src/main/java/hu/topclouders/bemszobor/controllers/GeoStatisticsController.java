package hu.topclouders.bemszobor.controllers;

import hu.topclouders.bemszobor.enums.ActionType;
import hu.topclouders.bemszobor.json.mapper.ActionTypeSerializer;
import hu.topclouders.bemszobor.service.GeoService;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
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

	private JsonFactory jsonFactory = new JsonFactory();

	@RequestMapping(value = "/stat/chart/geo/countries/{protestId}/{type}", method = RequestMethod.GET)
	@ResponseBody
	public String getCountries(@PathVariable("protestId") Long protestId,
			@PathVariable("type") String type) throws IOException {

		ActionType actionType = new ActionTypeSerializer().deserialize(type);

		Map<String, Long> countries;

		if (type.equalsIgnoreCase("all")) {
			countries = geoService.getCountriesByProtestId(protestId);
		} else {
			countries = geoService.getCountriesByProtestIdAndType(protestId,
					actionType);
		}

		return convertToJson(countries);
	}

	@RequestMapping(value = "/stat/chart/geo/cities/{protestId}/{country}/{type}", method = RequestMethod.GET)
	@ResponseBody
	public String getCities(@PathVariable("protestId") Long protestId,
			@PathVariable("country") String countryCode,
			@PathVariable("type") String type) throws IOException {

		ActionType actionType = new ActionTypeSerializer().deserialize(type);

		Map<String, Long> countries;

		if (type.equalsIgnoreCase("all")) {
			countries = geoService.getCitiesByProtestIdAndCountryCode(
					protestId, countryCode);
		} else {
			countries = geoService.getCitiesByProtestIdAndCountryCodeAndType(
					protestId, countryCode, actionType);
		}

		return convertToJson(countries);
	}

	private String convertToJson(Map<String, Long> countries)
			throws IOException, JsonGenerationException {
		StringWriter writer = new StringWriter();
		JsonGenerator jsonGenerator = jsonFactory.createJsonGenerator(writer);
		jsonGenerator.writeStartArray();

		for (String country : countries.keySet()) {
			jsonGenerator.writeStartObject();
			jsonGenerator.writeStringField("c", country);
			jsonGenerator.writeNumberField("p", countries.get(country));
			jsonGenerator.writeEndObject();
		}

		jsonGenerator.writeEndArray();

		jsonGenerator.flush();
		jsonGenerator.close();

		return writer.toString();
	}
}