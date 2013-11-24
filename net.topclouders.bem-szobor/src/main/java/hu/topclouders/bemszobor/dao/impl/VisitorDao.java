package hu.topclouders.bemszobor.dao.impl;

import hu.topclouders.bemszobor.dao.IVisitorRepository;
import hu.topclouders.bemszobor.domain.QVisitor;
import hu.topclouders.bemszobor.domain.Visitor;
import hu.topclouders.bemszobor.enums.ActionType;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPAQuery;

@Repository
public class VisitorDao extends AbstractJpaDao<Visitor> implements
		IVisitorRepository {

	@Override
	public List<Tuple> findCountriesByProtestId(Long protestId) {
		QVisitor qVisitor = QVisitor.visitor;

		return new JPAQuery(getEntityManager()).from(qVisitor)
				.where(qVisitor.protest.id.eq(protestId))
				.groupBy(qVisitor.location.country)
				.list(qVisitor.location.country, qVisitor.count());

	}

	@Override
	public List<Tuple> getCountriesByProtestIdAndActionType(Long protestId,
			ActionType actionType) {
		QVisitor qVisitor = QVisitor.visitor;

		return new JPAQuery(getEntityManager())
				.from(qVisitor)
				.where(qVisitor.protest.id.eq(protestId).and(
						qVisitor.actionType.eq(actionType)))
				.groupBy(qVisitor.location.country)
				.list(qVisitor.location.country, qVisitor.count());

	}

	@Override
	public List<Tuple> findCitiesByProtestIdAndCountryCode(Long protestId,
			String countryCode) {
		QVisitor qVisitor = QVisitor.visitor;
		return new JPAQuery(getEntityManager())
				.from(qVisitor)
				.where(qVisitor.protest.id.eq(protestId).and(
						qVisitor.location.country.eq(countryCode)))
				.groupBy(qVisitor.location.city)
				.list(qVisitor.location.city, qVisitor.count());
	}

	@Override
	public List<Tuple> getCitiesByProtestIdAndCountryCodeAndActionType(
			Long protestId, String countryCode, ActionType actionType) {
		QVisitor qVisitor = QVisitor.visitor;
		return new JPAQuery(getEntityManager())
				.from(qVisitor)
				.where(qVisitor.protest.id.eq(protestId)
						.and(qVisitor.location.country.eq(countryCode))
						.and(qVisitor.actionType.eq(actionType)))
				.groupBy(qVisitor.location.city)
				.list(qVisitor.location.city, qVisitor.count());
	}
}
