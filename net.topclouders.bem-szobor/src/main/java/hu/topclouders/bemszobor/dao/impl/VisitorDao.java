package hu.topclouders.bemszobor.dao.impl;

import hu.topclouders.bemszobor.dao.IVisitorDao;
import hu.topclouders.bemszobor.domain.QVisitor;
import hu.topclouders.bemszobor.domain.Visitor;
import hu.topclouders.bemszobor.enums.ActionType;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPAQuery;

@Repository
public class VisitorDao extends AbstractJpaDao<Visitor> implements
		IVisitorDao {

	@Override
	public List<Tuple> findCountriesByProtestId(Long demonstrationId) {
		QVisitor qVisitor = QVisitor.visitor;

		return new JPAQuery(getEntityManager()).from(qVisitor)
				.where(qVisitor.demonstration.id.eq(demonstrationId))
				.groupBy(qVisitor.location.country)
				.list(qVisitor.location.country, qVisitor.count());

	}

	@Override
	public List<Tuple> getCountriesByProtestIdAndActionType(Long demonstrationId,
			ActionType actionType) {
		QVisitor qVisitor = QVisitor.visitor;

		return new JPAQuery(getEntityManager())
				.from(qVisitor)
				.where(qVisitor.demonstration.id.eq(demonstrationId).and(
						qVisitor.actionType.eq(actionType)))
				.groupBy(qVisitor.location.country)
				.list(qVisitor.location.country, qVisitor.count());

	}

	@Override
	public List<Tuple> findCitiesByProtestIdAndCountryCode(Long demonstrationId,
			String countryCode) {
		QVisitor qVisitor = QVisitor.visitor;
		return new JPAQuery(getEntityManager())
				.from(qVisitor)
				.where(qVisitor.demonstration.id.eq(demonstrationId).and(
						qVisitor.location.country.eq(countryCode)))
				.groupBy(qVisitor.location.city)
				.list(qVisitor.location.city, qVisitor.count());
	}

	@Override
	public List<Tuple> getCitiesByProtestIdAndCountryCodeAndActionType(
			Long demonstrationId, String countryCode, ActionType actionType) {
		QVisitor qVisitor = QVisitor.visitor;
		return new JPAQuery(getEntityManager())
				.from(qVisitor)
				.where(qVisitor.demonstration.id.eq(demonstrationId)
						.and(qVisitor.location.country.eq(countryCode))
						.and(qVisitor.actionType.eq(actionType)))
				.groupBy(qVisitor.location.city)
				.list(qVisitor.location.city, qVisitor.count());
	}
}
