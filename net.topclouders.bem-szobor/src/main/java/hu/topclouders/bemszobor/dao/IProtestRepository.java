package hu.topclouders.bemszobor.dao;

import hu.topclouders.bemszobor.domain.Demonstration;

import java.util.Date;
import java.util.List;

import com.mysema.query.Tuple;

public interface IProtestRepository extends IGenericDao<Demonstration> {

	public List<Tuple> getActiveDemonstrators(Date date);

	public List<Tuple> getClosedProtests(Date date);

	public List<Tuple> getInProgressProtests(Date date);

}
