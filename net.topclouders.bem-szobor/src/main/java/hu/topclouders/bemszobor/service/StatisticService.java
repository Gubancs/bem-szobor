package hu.topclouders.bemszobor.service;

import hu.topclouders.bemszobor.domain.Person.Gender;
import hu.topclouders.bemszobor.domain.QVisitor;
import hu.topclouders.bemszobor.enums.ActionType;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.google.common.collect.Maps;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPAQuery;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class StatisticService {

	@PersistenceContext
	private EntityManager entityManager;

	public Map<Integer, Map<Gender, Long>> getGenderStatisticByProtest(
			Long protestId) {
		Assert.notNull(protestId);

		QVisitor qVisitor = QVisitor.visitor;

		List<Tuple> list = new JPAQuery(entityManager)
				.from(qVisitor)
				.where(qVisitor.demonstration.id.eq(protestId))
				.groupBy(qVisitor.person.age, qVisitor.person.gender)
				.orderBy(qVisitor.person.age.asc())
				.list(qVisitor.person.age, qVisitor.person.gender,
						qVisitor.count());

		return convertToMap(qVisitor, list);
	}

	public Map<Integer, Map<Gender, Long>> getGenderStatisticByProtestAndActionType(
			Long protestId, ActionType actionType) {
		Assert.notNull(protestId);
		Assert.notNull(actionType);

		QVisitor qVisitor = QVisitor.visitor;

		List<Tuple> list = new JPAQuery(entityManager)
				.from(qVisitor)
				.where(qVisitor.demonstration.id.eq(protestId).and(
						qVisitor.actionType.eq(actionType)))
				.groupBy(qVisitor.person.age, qVisitor.person.gender)
				.orderBy(qVisitor.person.age.asc())
				.list(qVisitor.person.age, qVisitor.person.gender,
						qVisitor.count());

		return convertToMap(qVisitor, list);
	}

	private Map<Integer, Map<Gender, Long>> convertToMap(QVisitor qVisitor,
			List<Tuple> list) {
		Map<Integer, Map<Gender, Long>> result = Maps.newHashMap();
		Integer age;
		Map<Gender, Long> genders;
		for (Tuple row : list) {

			age = row.get(qVisitor.person.age);

			genders = result.get(age);

			if (genders == null) {
				genders = Maps.newHashMap();
				genders.put(Gender.MALE, 0l);
				genders.put(Gender.FEMALE, 0l);
			}

			genders.put(row.get(qVisitor.person.gender),
					row.get(qVisitor.count()));

			result.put(age, genders);
		}
		return result;
	}

	public Map<ActionType, Long> getTotalStatistic(Long protestId) {

		Assert.notNull(protestId);

		QVisitor qVisitor = QVisitor.visitor;

		List<Tuple> list = new JPAQuery(entityManager).from(qVisitor)
				.where(qVisitor.demonstration.id.eq(protestId))
				.groupBy(qVisitor.actionType)
				.list(qVisitor.actionType, qVisitor.count());

		Map<ActionType, Long> result = Maps.newHashMap();

		for (Tuple row : list) {
			result.put(row.get(qVisitor.actionType), row.get(qVisitor.count()));
		}

		return result;
	}
}
