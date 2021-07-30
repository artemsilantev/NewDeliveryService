package com.artemsilantev.persistence.facade;

import com.artemsilantev.core.model.Product;
import com.artemsilantev.core.repository.ProductRepository;
import com.artemsilantev.persistence.mapper.ProductEntityMapper;
import com.artemsilantev.persistence.repository.JpaProductRepository;
import java.util.Collection;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JpaProductRepositoryFacade implements ProductRepository {

  private final JpaProductRepository repository;
  private final ProductEntityMapper mapper;

  @Override
  public Product create(Product entity) {
    return mapper.toTarget(repository.save(mapper.toSource(entity)));
  }

  @Override
  public Product get(Long id) {
    return mapper.toTarget(repository.getById(id));
  }

  @Override
  public Collection<Product> getAll() {
    return mapper.toTargetCollection(repository.findAll());
  }

  @Override
  public void delete(Long id) {
    repository.deleteById(id);
  }

  @Override
  public void update(Product product) {
    repository.save(mapper.toSource(product));
  }

  @Override
  public Boolean isNameExists(String name) {
    return repository.existsByName(name);
  }

  @Override
  public Boolean isNameExists(String name, Long id) {
    return repository.existsByNameAndIdIsNot(name, id);
  }
}
