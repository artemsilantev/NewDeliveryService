package com.artemsilantev.core.repositories.impl;

import com.artemsilantev.core.exceptions.DuplicateEntityException;
import com.artemsilantev.core.exceptions.NoRecordException;
import com.artemsilantev.core.model.BaseEntity;
import com.artemsilantev.core.repositories.AbstractRepository;
import com.artemsilantev.core.storages.AbstractDataStorage;
import java.util.Collection;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class AbstractRepositoryImpl<E extends BaseEntity>
    implements AbstractRepository<E> {

  private static final String ENTITY_STANDARD_NAME = "Entity";
  protected final AbstractDataStorage<E> dataStorage;

  @Override
  public E create(E entity) {
    fillReference(entity);
    var entityCreated = dataStorage.create(entity);
    save();
    return entityCreated;
  }

  @Override
  public E get(Long id) {
    var entities = getAll().stream()
        .filter(entity -> entity.getId().equals(id))
        .collect(Collectors.toList());
    if (entities.isEmpty()) {
      throw new NoRecordException(getEntityName(), id);
    } else if (entities.size() > 1) {
      throw new DuplicateEntityException(getEntityName(), id);
    }
    return entities.get(0);
  }

  @Override
  public Collection<E> getAll() {
    return dataStorage.getEntities();
  }

  @Override
  public void delete(Long id) {
    getAll().remove(get(id));
    save();
  }

  @Override
  public void update(E entity) {
    fillReference(entity);
    delete(entity.getId());
    getAll().add(entity);
    save();
  }

  protected void fillReference(E entity) {

  }

  protected String getEntityName() {
    return ENTITY_STANDARD_NAME;
  }

  @Override
  public void save() {
    dataStorage.save();
  }
}
