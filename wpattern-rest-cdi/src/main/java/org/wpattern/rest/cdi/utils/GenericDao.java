package org.wpattern.rest.cdi.utils;

import java.util.List;

public interface GenericDao<T extends BaseEntity> {

	public List<T> findAll();

	public T insert(T entity);

	public void update(T entity);

	public void delete(T entity);

	public T findById(Long id);

}
