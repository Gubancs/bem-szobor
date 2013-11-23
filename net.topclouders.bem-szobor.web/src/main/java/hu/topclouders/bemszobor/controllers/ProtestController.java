package hu.topclouders.bemszobor.controllers;

import hu.topclouders.bemszobor.domain.Protest;
import hu.topclouders.bemszobor.service.ProtestService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
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
	public String getActiveProtests() throws JsonGenerationException,
			IOException {
		Map<Protest, Long> protests = protestService.getActiveDemonstrations();

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		JsonFactory jsonFactory = new JsonFactory();
		JsonGenerator jsonGenerator = jsonFactory.createJsonGenerator(out);
		jsonGenerator.writeStartArray();
		
		for (Protest protest : protests.keySet()) {
			jsonGenerator.writeStartObject();
			jsonGenerator.writeStringField("protest", protest.getName());
			jsonGenerator.writeStringField("protest", protest.getName());
			jsonGenerator.writeNumberField("count", protests.get(protest));
			jsonGenerator.writeEndObject();
		}
		
		jsonGenerator.writeEndArray();
		
		jsonGenerator.flush();
		jsonGenerator.close();

		return new String(out.toByteArray());
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
