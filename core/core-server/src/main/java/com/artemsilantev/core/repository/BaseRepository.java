package com.artemsilantev.core.repository;

import com.artemsilantev.core.model.BaseEntity;
import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseRepository<E extends BaseEntity> {


  E create(E entity);

  E get(Long id);

  Collection<E> getAll();

  Page<E> find(Pageable pageable);

  void delete(Long id);

  E update(E e);

}
