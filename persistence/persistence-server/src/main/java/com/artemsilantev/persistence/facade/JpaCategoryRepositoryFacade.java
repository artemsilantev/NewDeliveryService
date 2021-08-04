package com.artemsilantev.persistence.facade;

import com.artemsilantev.core.exception.NoRecordException;
import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.Category;
import com.artemsilantev.core.repository.CategoryRepository;
import com.artemsilantev.persistence.model.CategoryEntity;
import com.artemsilantev.persistence.repository.JpaCategoryRepository;
import com.artemsilantev.persistence.repository.JpaProductRepository;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public class JpaCategoryRepositoryFacade extends JpaBaseRepositoryFacade<Category, CategoryEntity>
    implements CategoryRepository {

  private final JpaProductRepository productRepository;

  public JpaCategoryRepositoryFacade(
      JpaRepository<CategoryEntity, Long> repository,
      Mapper<Category, CategoryEntity> mapper,
      JpaProductRepository productRepository) {
    super(repository, mapper);
    this.productRepository = productRepository;
  }

  @Override
  public Collection<Category> getRootCategories() {
    return mapper.toTargetCollection(((JpaCategoryRepository) repository).findAllByParentNull());
  }

  @Override
  public Collection<Category> getChildrenCategories() {
    return mapper.toTargetCollection(((JpaCategoryRepository) repository).findAllByParentNotNull());
  }

  @Override
  public Boolean isNameExists(String name) {
    return ((JpaCategoryRepository) repository).existsByName(name);
  }

  @Override
  public Boolean isNameExists(String name, Long id) {
    return ((JpaCategoryRepository) repository).existsByNameAndIdIsNot(name, id);
  }

  @Override
  protected NoRecordException createNoRecordException(Long id, String entityName) {
    return super.createNoRecordException(id, "Category");
  }
}
