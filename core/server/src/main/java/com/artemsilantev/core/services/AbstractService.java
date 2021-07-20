package com.artemsilantev.core.services;

import java.util.Collection;

public interface AbstractService<T, S> {

  T get(Long id);

  T create(S entity);

  Collection<T> getAll();

  void delete(Long id);

  void save();
}
