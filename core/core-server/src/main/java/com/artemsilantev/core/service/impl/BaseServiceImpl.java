package com.artemsilantev.core.service.impl;

import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.BaseEntity;
import com.artemsilantev.core.repository.BaseRepository;
import com.artemsilantev.core.service.BaseService;
import java.util.Collection;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class BaseServiceImpl<T, S extends BaseEntity> implements
    BaseService<T, S> {

  protected final Mapper<T, S> mapperDto;
  protected final BaseRepository<S> baseRepository;

  @Override
  public T get(Long id) {
    return mapperDto.toTarget(baseRepository.get(id));
  }

  @Override
  public T create(T entity) {
    var sourceEntity = mapperDto.toSource(entity);
    return mapperDto.toTarget(baseRepository.create(sourceEntity));
  }

  @Override
  public Collection<T> getAll() {
    return mapperDto.toTargetCollection(baseRepository.getAll());
  }

  @Override
  public void delete(Long id) {
    baseRepository.delete(id);
  }

  @Override
  public void update(T entity) {
    var sourceEntity = mapperDto.toSource(entity);
    baseRepository.update(sourceEntity);
  }
}
