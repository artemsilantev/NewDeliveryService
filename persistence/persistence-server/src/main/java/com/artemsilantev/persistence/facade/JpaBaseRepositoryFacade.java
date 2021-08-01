package com.artemsilantev.persistence.facade;

import com.artemsilantev.core.exception.NoRecordException;
import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.BaseEntity;
import com.artemsilantev.core.repository.BaseRepository;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

@RequiredArgsConstructor
public abstract class JpaBaseRepositoryFacade<T extends BaseEntity, S>
    implements BaseRepository<T> {

  protected final JpaRepository<S, Long> repository;
  protected final Mapper<T, S> mapper;

  @Override
  public T create(T entity) {
    return mapper.toTarget(repository.save(mapper.toSource(entity)));
  }

  @Override
  public T get(Long id) {
    return mapper.toTarget(repository.findById(id).orElseThrow(() ->
        createNoRecordException(id, "entity")));
  }

  @Override
  public Collection<T> getAll() {
    return mapper.toTargetCollection(repository.findAll());
  }

  @Override
  public void delete(Long id) {
    repository.deleteById(id);
  }

  @Override
  public void update(T entity) {
    repository.save(mapper.toSource(entity));
  }

  protected NoRecordException createNoRecordException(Long id, String entityName) {
    return new NoRecordException(String.format("%s was not found by this id (%d)",
        entityName, id));
  }
}
