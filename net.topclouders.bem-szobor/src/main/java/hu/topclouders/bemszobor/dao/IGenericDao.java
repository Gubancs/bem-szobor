package hu.topclouders.bemszobor.dao;

import java.util.List;

public interface IGenericDao<T> {

	public T save(T entity);

	public T update(T entity);

	public void delete(T entity);

	public T findOne(Long id);

	public List<T> findAll();

}
