package com.artemsilantev.persistence.facade;

import com.artemsilantev.core.exception.NoRecordException;
import com.artemsilantev.core.model.Category;
import com.artemsilantev.core.repository.CategoryRepository;
import com.artemsilantev.persistence.mapper.CategoryEntityMapper;
import com.artemsilantev.persistence.repository.JpaCategoryRepository;
import com.artemsilantev.persistence.repository.JpaProductRepository;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class JpaCategoryRepositoryFacade implements CategoryRepository {

  private final JpaCategoryRepository repository;
  private final JpaProductRepository productRepository;
  private final CategoryEntityMapper mapper;

  @Override
  public Collection<Category> getRootCategories() {
    return mapper.toTargetCollection(repository.findAllByParentNull());
  }

  @Override
  public Collection<Category> getChildrenCategories() {
    return mapper.toTargetCollection(repository.findAllByParentNotNull());
  }

  @Override
  public Boolean isNameExists(String name) {
    return repository.existsByName(name);
  }

  @Override
  public Boolean isNameExists(String name, Long id) {
    return repository.existsByNameAndIdIsNot(name, id);
  }

  @Override
  public Category create(Category entity) {
    return mapper.toTarget(repository.save(mapper.toSource(entity)));
  }

  @Override
  public Category get(Long id) {
    try {
      return mapper.toTarget(repository.getById(id));
    } catch (Exception exception) {
      throw new NoRecordException((String.format("%s was not found by this id (%d)",
          "Category", id)));
    }
  }

  @Override
  public Collection<Category> getAll() {
    return mapper.toTargetCollection(repository.findAll());
  }

  @Override
  public void delete(Long id) {
    repository.findAllByParent_Id(id).forEach(category ->
        category.setParent(null));

    productRepository.findAll().forEach(product ->
        product.getCategories().removeIf(categoryEntity ->
            categoryEntity.getId().equals(id)));
    repository.deleteById(id);
  }

  @Override
  public void update(Category category) {
    repository.save(mapper.toSource(category));
  }
}
