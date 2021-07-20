package com.artemsilantev.core.services.impl;

import com.artemsilantev.core.mappers.Mapper;
import com.artemsilantev.core.model.BaseEntity;
import com.artemsilantev.core.repositories.AbstractRepository;
import com.artemsilantev.core.services.AbstractService;
import java.util.Collection;
import java.util.stream.Collectors;
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
  public T create(S entity) {
    entity.setId(abstractRepository.create(entity).getId());
    return mapperDTO.toTarget(entity);
  }

  @Override
  public Collection<T> getAll() {
    return abstractRepository.getAll().stream()
        .map(mapperDTO::toTarget)
        .collect(Collectors.toList());
  }

  @Override
  public void save() {
    abstractRepository.save();
  }

  @Override
  public void delete(Long id) {
    abstractRepository.delete(id);
  }
}
