package com.artemsilantev.core.storage;

import com.artemsilantev.core.model.BaseEntity;
import java.util.Collection;

public interface BaseDataStorage<E extends BaseEntity> {

  Collection<E> getEntities();

  E create(E entity);

  void save();
}
