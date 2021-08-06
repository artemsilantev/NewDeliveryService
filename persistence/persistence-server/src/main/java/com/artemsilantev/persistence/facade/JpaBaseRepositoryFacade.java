package com.artemsilantev.persistence.facade;

import com.artemsilantev.core.exception.NoRecordException;
import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.BaseEntity;
import com.artemsilantev.core.repository.BaseRepository;
import java.util.Collection;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public abstract class JpaBaseRepositoryFacade<T extends BaseEntity, S>
    implements BaseRepository<T> {

  protected final JpaRepository<S, Long> repository;
  protected final Mapper<T, S> mapper;

  @Override
  @Transactional
  public T create(T entity) {
    return save(entity);
  }

  @Override
  @Transactional(readOnly = true)
  public T get(Long id) {
    try {
      return mapper.toTarget(repository.getById(id));
    } catch (EntityNotFoundException notFoundException) {
      throw createNoRecordException(id, "entity");
    }
  }

  @Override
  @Transactional(readOnly = true)
  public Collection<T> getAll() {
    return mapper.toTargetCollection(repository.findAll());
  }

  @Override
  @Transactional(readOnly = true)
  public Page<T> find(Pageable pageable) {
    return repository.findAll(pageable).map(mapper::toTarget);
  }

  @Override
  @Transactional
  public void delete(Long id) {
    try {
      repository.deleteById(id);
    } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
      throw createNoRecordException(id, "entity");
    }
  }

  @Override
  @Transactional
  public T update(T entity) {
    if (!repository.existsById(entity.getId())) {
      throw createNoRecordException(entity.getId(), "entity");
    }
    return save(entity);
  }

  protected NoRecordException createNoRecordException(Long id, String entityName) {
    return new NoRecordException(String.format("%s was not found by this id (%d)",
        entityName, id));
  }

  protected T save(T entity) {
    return mapper.toTarget(repository.save(mapper.toSource(entity)));
  }
}
