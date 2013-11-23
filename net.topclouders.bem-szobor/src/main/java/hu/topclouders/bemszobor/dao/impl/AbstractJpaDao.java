package hu.topclouders.bemszobor.dao.impl;

import hu.topclouders.bemszobor.dao.IGenericDao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.jpa.impl.JPADeleteClause;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.EntityPath;
import com.mysema.query.types.path.PathBuilder;
import com.mysema.util.ReflectionUtils;

@Transactional(propagation = Propagation.REQUIRED)
public class AbstractJpaDao<T extends Serializable> implements IGenericDao<T> {

	@PersistenceContext
	private EntityManager entityManager;

	private Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public AbstractJpaDao() {
		this.persistentClass = (Class<T>) ReflectionUtils
				.getTypeParameterAsClass(getClass().getGenericSuperclass(), 0);
	}

	public T save(T entity) {
		entityManager.persist(entity);
		return entity;
	}

	public T update(T entity) {
		entity = entityManager.merge(entity);
		return entity;
	}

	public void delete(T entity) {
		entityManager.remove(entity);
	}

	public T findOne(Long key) {
		return entityManager.find(persistentClass, key);
	}

	public List<T> findAll() {
		PathBuilder<T> path = new PathBuilder<T>(persistentClass, "entity");
		return createJPAQuery(path).list(path);
	}

	protected T findByNamedQuerySingleResult(String queryName,
			Object... parameters) {
		TypedQuery<T> query = this.createTypedQuery(queryName);

		for (int i = 0; i < parameters.length; i++) {

			query.setParameter(i + 1, parameters[i]);
		}
		return query.getSingleResult();
	}

	protected List<T> findByNamedQuery(String queryName, Object... parameters) {
		TypedQuery<T> query = this.createTypedQuery(queryName);

		for (int i = 0; i < parameters.length; i++) {

			query.setParameter(i + 1, parameters[i]);
		}
		return query.getResultList();
	}

	protected TypedQuery<T> createTypedQuery(String queryName) {
		return entityManager.createNamedQuery(queryName, persistentClass);
	}

	protected JPAQuery createJPAQuery(EntityPath<T> path) {
		return new JPAQuery(entityManager).from(path);
	}

	protected JPADeleteClause deleteClause(EntityPath<T> path) {
		return new JPADeleteClause(entityManager, path);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
}
