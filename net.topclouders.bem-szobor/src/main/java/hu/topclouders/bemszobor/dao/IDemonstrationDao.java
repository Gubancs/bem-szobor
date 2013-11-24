package hu.topclouders.bemszobor.dao;

import hu.topclouders.bemszobor.domain.Demonstration;

import java.util.Date;
import java.util.List;

import com.mysema.query.Tuple;

public interface IDemonstrationDao extends IGenericDao<Demonstration> {

	public List<Tuple> getActiveDemonstrators(Date date);

	public List<Tuple> getClosedDemonstrations(Date date);

	public List<Tuple> getInProgressProtests(Date date);

}
