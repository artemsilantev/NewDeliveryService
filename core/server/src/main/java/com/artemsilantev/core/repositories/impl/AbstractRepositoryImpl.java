package com.artemsilantev.core.repositories.impl;

import com.artemsilantev.core.exceptions.DuplicateEntityException;
import com.artemsilantev.core.exceptions.NoRecordException;
import java.util.Collection;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import com.artemsilantev.core.model.BaseEntity;
import com.artemsilantev.core.repositories.AbstractRepository;
import com.artemsilantev.core.storages.AbstractDataStorage;

@AllArgsConstructor
public abstract class AbstractRepositoryImpl<E extends BaseEntity>
    implements AbstractRepository<E> {

  protected final AbstractDataStorage<E> dataStorage;

  @Override
  public E create(E entity) {
    return dataStorage.create(entity);
  }

  @Override
  public E get(Long id) {
    var entities = getAll().stream()
        .filter(entity -> entity.getId().equals(id))
        .collect(Collectors.toList());
    if (entities.isEmpty()) {
      throw new NoRecordException(dataStorage.getClass().getSimpleName(), id);
    } else if (entities.size() > 1) {
      throw new DuplicateEntityException(dataStorage.getClass().getSimpleName(), id);
    }
    return entities.get(0);
  }

  @Override
  public Collection<E> getAll() {
    return dataStorage.getEntities();
  }

  @Override
  public void delete(Long id) throws NoRecordException {
    E entityToDelete = dataStorage.getEntities()
        .stream()
        .filter(entity -> entity.getId() == id)
        .findFirst()
        .orElseThrow(() -> new NoRecordException(dataStorage.getClass().getSimpleName(), id));
    dataStorage.getEntities().remove(entityToDelete);
  }

  @Override
  public void save() {
    dataStorage.save();
  }
}