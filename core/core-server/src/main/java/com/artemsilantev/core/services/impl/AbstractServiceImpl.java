package com.artemsilantev.core.services.impl;

import com.artemsilantev.core.mappers.Mapper;
import com.artemsilantev.core.model.BaseEntity;
import com.artemsilantev.core.repositories.AbstractRepository;
import com.artemsilantev.core.services.AbstractService;
import java.util.Collection;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class AbstractServiceImpl<T, S extends BaseEntity> implements
    AbstractService<T, S> {

  protected final Mapper<T, S> mapperDTO;
  protected final AbstractRepository<S> abstractRepository;

  @Override
  public T get(Long id) {
    return mapperDTO.toTarget(abstractRepository.get(id));
  }

  @Override
  public T create(T entity) {
    var sourceEntity = mapperDTO.toSource(entity);
    return mapperDTO.toTarget(abstractRepository.create(sourceEntity));
  }

  @Override
  public Collection<T> getAll() {
    return mapperDTO.toTargetCollection(abstractRepository.getAll());
  }

  @Override
  public void save() {
    abstractRepository.save();
  }

  @Override
  public void delete(Long id) {
    abstractRepository.delete(id);
  }

  @Override
  public void update(T entity) {
    var sourceEntity = mapperDTO.toSource(entity);
    abstractRepository.update(sourceEntity);
  }
}
