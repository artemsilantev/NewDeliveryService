package com.artemsilantev.persistence.facade;

import com.artemsilantev.core.exception.IllegalEntityException;
import com.artemsilantev.core.exception.NoRecordException;
import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.Product;
import com.artemsilantev.core.repository.ProductRepository;
import com.artemsilantev.persistence.model.ProductEntity;
import com.artemsilantev.persistence.repository.JpaProductRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public class JpaProductRepositoryFacade extends JpaBaseRepositoryFacade<Product, ProductEntity>
    implements ProductRepository {

  public JpaProductRepositoryFacade(
      JpaRepository<ProductEntity, Long> repository,
      Mapper<Product, ProductEntity> mapper) {
    super(repository, mapper);
  }

  @Override
  @Transactional(readOnly = true)
  public Boolean isNameExists(String name) {
    return ((JpaProductRepository) repository).existsByName(name);
  }

  @Override
  @Transactional(readOnly = true)
  public Boolean isNameExists(String name, Long id) {
    return ((JpaProductRepository) repository).existsByNameAndIdIsNot(name, id);
  }

  @Override
  protected NoRecordException createNoRecordException(Long id, String entityName) {
    return super.createNoRecordException(id, "Product");
  }

  @Override
  protected IllegalEntityException createIllegalEntityException(
      DataIntegrityViolationException exception, String entityName) {
    return super.createIllegalEntityException(exception, "Product");
  }
}
