package hu.topclouders.bemszobor.service;

import hu.topclouders.bemszobor.domain.QVisitor;
import hu.topclouders.bemszobor.enums.ActionType;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.google.common.collect.Maps;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPAQuery;

@Service
public class GeoService {

	@PersistenceContext
	private EntityManager entityManager;

	public Map<String, Long> getCountriesByProtestId(Long protestId) {
		Assert.notNull(protestId);

		QVisitor qVisitor = QVisitor.visitor;
		List<Tuple> list = new JPAQuery(entityManager).from(qVisitor)
				.where(qVisitor.protest.id.eq(protestId))
				.groupBy(qVisitor.country)
				.list(qVisitor.country, qVisitor.count());

		Map<String, Long> result = Maps.newHashMap();

		for (Tuple row : list) {
			result.put(row.get(qVisitor.country), row.get(qVisitor.count()));
		}

		return result;
	}

	public Map<String, Long> getCountriesByProtestIdAndType(Long protestId,
			ActionType actionType) {

		Assert.notNull(protestId);
		Assert.notNull(actionType);

		QVisitor qVisitor = QVisitor.visitor;
		List<Tuple> list = new JPAQuery(entityManager)
				.from(qVisitor)
				.where(qVisitor.protest.id.eq(protestId).and(
						qVisitor.actionType.eq(actionType)))
				.groupBy(qVisitor.country)
				.list(qVisitor.country, qVisitor.count());

		Map<String, Long> result = Maps.newHashMap();

		for (Tuple row : list) {
			result.put(row.get(qVisitor.country), row.get(qVisitor.count()));
		}

		return result;

	}

	public Map<String, Long> getCitiesByProtestIdAndCountryCode(Long protestId,
			String countryCode) {
		Assert.notNull(protestId);
		Assert.notNull(countryCode);

		QVisitor qVisitor = QVisitor.visitor;
		List<Tuple> list = new JPAQuery(entityManager)
				.from(qVisitor)
				.where(qVisitor.protest.id.eq(protestId).and(
						qVisitor.country.eq(countryCode)))
				.groupBy(qVisitor.city).list(qVisitor.city, qVisitor.count());

		Map<String, Long> result = Maps.newHashMap();

		for (Tuple row : list) {
			result.put(row.get(qVisitor.city), row.get(qVisitor.count()));
		}

		return result;
	}

	public Map<String, Long> getCitiesByProtestIdAndCountryCodeAndType(
			Long protestId, String countryCode, ActionType actionType) {
		Assert.notNull(protestId);
		Assert.notNull(countryCode);

		QVisitor qVisitor = QVisitor.visitor;
		List<Tuple> list = new JPAQuery(entityManager)
				.from(qVisitor)
				.where(qVisitor.protest.id.eq(protestId)
						.and(qVisitor.country.eq(countryCode))
						.and(qVisitor.actionType.eq(actionType)))
				.groupBy(qVisitor.city).list(qVisitor.city, qVisitor.count());

		Map<String, Long> result = Maps.newHashMap();

		for (Tuple row : list) {
			result.put(row.get(qVisitor.city), row.get(qVisitor.count()));
		}

		return result;
	}

}
