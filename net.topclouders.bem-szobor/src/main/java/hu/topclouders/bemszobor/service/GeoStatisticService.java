package hu.topclouders.bemszobor.service;

import hu.topclouders.bemszobor.dao.IVisitorDao;
import hu.topclouders.bemszobor.domain.QVisitor;
import hu.topclouders.bemszobor.enums.ActionType;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.google.common.collect.Maps;
import com.mysema.query.Tuple;

@Service
public class GeoStatisticService {

	@Autowired
	private IVisitorDao visitorRepository;

	public Map<String, Long> getCountriesByProtestId(Long protestId) {
		Assert.notNull(protestId);

		QVisitor qVisitor = QVisitor.visitor;
		Map<String, Long> result = Maps.newHashMap();

		for (Tuple row : visitorRepository.findCountriesByProtestId(protestId)) {
			result.put(row.get(qVisitor.location.country),
					row.get(qVisitor.count()));
		}

		return result;
	}

	public Map<String, Long> getCountriesByProtestIdAndType(Long protestId,
			ActionType actionType) {

		Assert.notNull(protestId);
		Assert.notNull(actionType);

		QVisitor qVisitor = QVisitor.visitor;

		Map<String, Long> result = Maps.newHashMap();

		for (Tuple row : visitorRepository
				.getCountriesByProtestIdAndActionType(protestId, actionType)) {
			result.put(row.get(qVisitor.location.country),
					row.get(qVisitor.count()));
		}

		return result;

	}

	public Map<String, Long> getCitiesByProtestIdAndCountryCode(Long protestId,
			String countryCode) {
		Assert.notNull(protestId);
		Assert.notNull(countryCode);

		QVisitor qVisitor = QVisitor.visitor;

		Map<String, Long> result = Maps.newHashMap();
		for (Tuple row : visitorRepository.findCitiesByProtestIdAndCountryCode(
				protestId, countryCode)) {
			result.put(row.get(qVisitor.location.city),
					row.get(qVisitor.count()));
		}

		return result;
	}

	public Map<String, Long> getCitiesByProtestIdAndCountryCodeAndType(
			Long protestId, String countryCode, ActionType actionType) {
		Assert.notNull(protestId);
		Assert.notNull(countryCode);

		QVisitor qVisitor = QVisitor.visitor;
		List<Tuple> list = visitorRepository
				.getCitiesByProtestIdAndCountryCodeAndActionType(protestId,
						countryCode, actionType);

		Map<String, Long> result = Maps.newHashMap();

		for (Tuple row : list) {
			result.put(row.get(qVisitor.location.city),
					row.get(qVisitor.count()));
		}

		return result;
	}

}
