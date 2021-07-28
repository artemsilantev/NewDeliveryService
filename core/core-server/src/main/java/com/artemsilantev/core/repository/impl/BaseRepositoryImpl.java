package com.artemsilantev.core.repository.impl;

import com.artemsilantev.core.exception.DuplicateEntityException;
import com.artemsilantev.core.exception.NoRecordException;
import com.artemsilantev.core.model.BaseEntity;
import com.artemsilantev.core.repository.BaseRepository;
import com.artemsilantev.core.storage.BaseDataStorage;
import java.util.Collection;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class BaseRepositoryImpl<E extends BaseEntity>
    implements BaseRepository<E> {

  private static final String ENTITY_STANDARD_NAME = "Entity";
  protected final BaseDataStorage<E> dataStorage;

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
      throw new NoRecordException(String.format("%s was not found by this id (%d)",
          getEntityName(), id));
    } else if (entities.size() > 1) {
      throw new DuplicateEntityException(String.format("Object in this storage %s with id (%d) has duplicate",
          getEntityName(), id));
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
