package com.artemsilantev.persistence.facade;

import com.artemsilantev.core.exception.IllegalEntityException;
import com.artemsilantev.core.exception.NoRecordException;
import com.artemsilantev.core.filter.CategoryFilter;
import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.Category;
import com.artemsilantev.core.repository.CategoryRepository;
import com.artemsilantev.persistence.model.CategoryEntity;
import com.artemsilantev.persistence.repository.JpaCategoryRepository;
import com.artemsilantev.persistence.specification.CategorySpecification;
import java.util.Collection;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public class JpaCategoryRepositoryFacade extends JpaBaseRepositoryFacade<Category, CategoryEntity>
    implements CategoryRepository {

  private final CategorySpecification specification;

  public JpaCategoryRepositoryFacade(
      JpaRepository<CategoryEntity, Long> repository,
      Mapper<Category, CategoryEntity> mapper,
      CategorySpecification specification) {
    super(repository, mapper);
    this.specification = specification;
  }

  @Override
  @Transactional(readOnly = true)
  public Collection<Category> getRootCategories() {
    return mapper.toTargetCollection(((JpaCategoryRepository) repository).findAllByParentNull());
  }

  @Override
  @Transactional(readOnly = true)
  public Collection<Category> getChildrenCategories() {
    return mapper.toTargetCollection(((JpaCategoryRepository) repository).findAllByParentNotNull());
  }

  @Override
  @Transactional(readOnly = true)
  public Page<Category> search(CategoryFilter filter, Pageable pageable) {
    return ((JpaCategoryRepository) repository).findAll(specification.get(filter), pageable)
        .map(mapper::toTarget);
  }

  @Override
  @Transactional(readOnly = true)
  public Boolean isNameExists(String name) {
    return ((JpaCategoryRepository) repository).existsByName(name);
  }

  @Override
  @Transactional(readOnly = true)
  public Boolean isNameExists(String name, Long id) {
    return ((JpaCategoryRepository) repository).existsByNameAndIdIsNot(name, id);
  }

  @Override
  protected NoRecordException createNoRecordException(Long id, String entityName) {
    return super.createNoRecordException(id, "Category");
  }

  @Override
  protected IllegalEntityException createIllegalEntityException(
      DataIntegrityViolationException exception, String entityName) {
    return super.createIllegalEntityException(exception, "Category");
  }
}
