package hu.topclouders.bemszobor.controllers;

import hu.topclouders.bemszobor.domain.Person.Gender;
import hu.topclouders.bemszobor.enums.ActionType;
import hu.topclouders.bemszobor.json.mapper.ActionTypeSerializer;
import hu.topclouders.bemszobor.service.StatisticService;

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
public class StatisticsController {

	@Autowired
	private StatisticService statisticService;

	private JsonFactory jsonFactory = new JsonFactory();

	@RequestMapping(value = "/stat/total/{protestId}", method = RequestMethod.GET)
	@ResponseBody
	public String getTotalStatistic(@PathVariable("protestId") Long protestId)
			throws IOException {

		Map<ActionType, Long> totalStat = statisticService
				.getTotalStatistic(protestId);

		Map<Integer, Map<Gender, Long>> genderStat = statisticService
				.getGenderStatisticByProtest(protestId);

		String json = convertTotalStatisticToJson(totalStat);
		json = json.concat(convertGenderStatisticToJson(genderStat));

		return json;

	}

	private String convertTotalStatisticToJson(
			Map<ActionType, Long> totalStatistic) throws IOException,
			JsonGenerationException {
		StringWriter writer = new StringWriter();
		JsonGenerator gen = jsonFactory.createJsonGenerator(writer);

		gen.writeStartObject();
		ActionTypeSerializer actionTypeSerializer = new ActionTypeSerializer();
		for (ActionType actionType : totalStatistic.keySet()) {

			gen.writeNumberField(actionTypeSerializer.serialize(actionType),
					totalStatistic.get(actionType));

		}
		gen.writeEndObject();

		gen.flush();
		gen.close();
		return writer.toString();
	}

	@RequestMapping(value = "/stat/chart/age/{protestId}/{type}", method = RequestMethod.GET)
	@ResponseBody
	public String getCurrentStatistic(
			@PathVariable("protestId") Long protestId,
			@PathVariable("type") String type) throws IOException {

		ActionType actionType = new ActionTypeSerializer().deserialize(type);

		Map<Integer, Map<Gender, Long>> statistic;

		if (type.equalsIgnoreCase("all")) {
			statistic = statisticService.getGenderStatisticByProtest(protestId);
		} else {

			if (actionType == null) {
				return "";
			}
			statistic = statisticService
					.getGenderStatisticByProtestAndActionType(protestId,
							actionType);
		}

		return convertGenderStatisticToJson(statistic);
	}

	private String convertGenderStatisticToJson(
			Map<Integer, Map<Gender, Long>> statistic) throws IOException,
			JsonGenerationException {
		StringWriter writer = new StringWriter();
		JsonGenerator gen = jsonFactory.createJsonGenerator(writer);

		gen.writeStartArray();
		for (Integer age : statistic.keySet()) {
			gen.writeStartObject();
			gen.writeNumberField("age", age);

			for (Gender gender : statistic.get(age).keySet()) {
				gen.writeNumberField(gender.name().substring(0, 1)
						.toLowerCase(), statistic.get(age).get(gender));
			}
			gen.writeEndObject();
		}
		gen.writeEndArray();

		gen.flush();
		gen.close();
		return writer.toString();
	}

}
