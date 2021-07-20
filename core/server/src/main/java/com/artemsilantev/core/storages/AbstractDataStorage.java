package com.artemsilantev.core.storages;

import java.util.Collection;
import com.artemsilantev.core.model.BaseEntity;

public interface AbstractDataStorage<E extends BaseEntity> {

  Collection<E> getEntities();

  E create(E entity);

  void save();
}
