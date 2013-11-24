package hu.topclouders.bemszobor.dao;

import java.util.List;

import com.mysema.query.Tuple;

import hu.topclouders.bemszobor.domain.Visitor;
import hu.topclouders.bemszobor.enums.ActionType;

public interface IVisitorDao extends IGenericDao<Visitor> {

	public List<Tuple> findCountriesByProtestId(Long protestId);

	public List<Tuple> getCountriesByProtestIdAndActionType(Long protestId,
			ActionType actionType);

	public List<Tuple> findCitiesByProtestIdAndCountryCode(Long protestId,
			String countryCode);

	public List<Tuple> getCitiesByProtestIdAndCountryCodeAndActionType(
			Long protestId, String countryCode, ActionType actionType);
}
