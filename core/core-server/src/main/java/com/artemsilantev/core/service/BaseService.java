package com.artemsilantev.core.service;

import java.util.Collection;

public interface BaseService<T, S> {

  T get(Long id);

  T create(T entity);

  Collection<T> getAll();

  void delete(Long id);

  T update(T entity);
}
