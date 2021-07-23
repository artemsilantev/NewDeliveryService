package com.artemsilantev.core.services;

import java.util.Collection;

public interface AbstractService<T, S> {

  T get(Long id);

  T create(T entity);

  Collection<T> getAll();

  void delete(Long id);

  void save();

  void update(T entity);
}
