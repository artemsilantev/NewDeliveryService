package com.artemsilantev.core.repositories;

import com.artemsilantev.core.model.BaseEntity;
import java.util.Collection;

public interface AbstractRepository<E extends BaseEntity> {


  E create(E entity);

  E get(Long id);

  Collection<E> getAll();

  void save();

  void delete(Long id);

  void update(E e);

}
