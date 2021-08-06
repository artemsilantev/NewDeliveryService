package com.artemsilantev.persistence.facade;

import com.artemsilantev.core.exception.NoRecordException;
import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.Category;
import com.artemsilantev.core.repository.CategoryRepository;
import com.artemsilantev.persistence.model.CategoryEntity;
import com.artemsilantev.persistence.repository.JpaCategoryRepository;
import com.artemsilantev.persistence.specification.CategorySpecification;
import java.util.Collection;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public class JpaCategoryRepositoryFacade extends JpaBaseRepositoryFacade<Category, CategoryEntity>
    implements CategoryRepository {

  public JpaCategoryRepositoryFacade(
      JpaRepository<CategoryEntity, Long> repository,
      Mapper<Category, CategoryEntity> mapper) {
    super(repository, mapper);
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
  public Collection<Category> getNameStartWith(String name, Sort sort) {
    return mapper.toTargetCollection(
        ((JpaCategoryRepository) repository).findAll(CategorySpecification.nameStartWith(name),
            sort));
  }

  @Override
  @Transactional(readOnly = true)
  public Collection<Category> getNameStartWithAndParent(String name, Long parentId, Sort sort) {
    return mapper.toTargetCollection(
        ((JpaCategoryRepository) repository).findAll(
            Specification.where(
                CategorySpecification.nameStartWith(name)
                    .and(CategorySpecification.parentIdEqual(parentId))),
            sort));
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
}
