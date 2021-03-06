package com.artemsilantev.core.service;

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<T, S> {

  T get(Long id);

  T create(T entity);

  Collection<T> getAll();

  Page<T> find(Pageable pageable);

  void delete(Long id);

  T update(T entity);
}
