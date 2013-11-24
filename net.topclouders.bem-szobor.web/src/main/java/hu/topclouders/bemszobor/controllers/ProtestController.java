package hu.topclouders.bemszobor.controllers;

import hu.topclouders.bemszobor.domain.Demonstration;
import hu.topclouders.bemszobor.service.ProtestService;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProtestController {

	private static final String DEFAULT_DATE_FORMAT = "yyyy.MM.dd. HH:mm z";

	@Autowired
	private ProtestService protestService;

	private JsonFactory jsonFactory = new JsonFactory();

	@RequestMapping(value = "/protests/active", method = RequestMethod.GET)
	@ResponseBody
	public String getActiveProtests() throws JsonGenerationException,
			IOException {
		Map<Demonstration, Long> protests = protestService.getActiveDemonstrations();

		return toJson(protests);
	}

	@RequestMapping(value = "/protests/closed", method = RequestMethod.GET)
	@ResponseBody
	public String getClosedProtests() throws IOException {

		Map<Demonstration, Long> protests = protestService.getClosedDemonstrations();

		return toJson(protests);
	}

	@RequestMapping(value = "/protests/inprogress", method = RequestMethod.GET)
	@ResponseBody
	public String getInProgessProtests() throws IOException {
		Map<Demonstration, Long> protests = protestService.getInProgressDemonstrations();

		return toJson(protests);

	}

	private String toJson(Map<Demonstration, Long> protests) throws IOException,
			JsonGenerationException {
		StringWriter writer = new StringWriter();
		JsonGenerator jsonGenerator = jsonFactory.createJsonGenerator(writer);
		jsonGenerator.writeStartArray();

		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);

		for (Demonstration protest : protests.keySet()) {
			jsonGenerator.writeStartObject();
			jsonGenerator.writeNumberField("protest_id", protest.getId());
			jsonGenerator.writeStringField("date",
					sdf.format(protest.getStart()));
			jsonGenerator.writeStringField("desc", protest.getName());
			jsonGenerator.writeStringField("location", protest.getAddress());
			jsonGenerator.writeNumberField("people", protests.get(protest));
			jsonGenerator.writeEndObject();
		}

		jsonGenerator.writeEndArray();

		jsonGenerator.flush();
		jsonGenerator.close();
		return writer.toString();
	}
}
