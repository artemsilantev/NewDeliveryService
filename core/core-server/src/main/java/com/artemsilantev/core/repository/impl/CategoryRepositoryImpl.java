package com.artemsilantev.core.repository.impl;

import com.artemsilantev.core.model.Category;
import com.artemsilantev.core.repository.CategoryRepository;
import com.artemsilantev.core.storage.CategoryDataStorage;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;

public class CategoryRepositoryImpl extends BaseRepositoryImpl<Category>
    implements CategoryRepository {

  public CategoryRepositoryImpl(CategoryDataStorage categoryDataStorage) {
    super(categoryDataStorage);
  }

  @Override
  public Collection<Category> getRootCategories() {
    return getAll().stream()
        .filter(category -> category.getParent() == null)
        .collect(Collectors.toList());
  }

  @Override
  public Collection<Category> getChildrenCategories() {
    return getAll().stream()
        .filter(category -> category.getParent() != null)
        .collect(Collectors.toList());
  }

  @Override
  public Collection<Category> search(String name, Long parentId,
      Sort sort) {
    return getAll();
  }

  @Override
  public Boolean isNameExists(String name) {
    return getAll().stream()
        .anyMatch(category -> category.getName().equals(name));
  }

  @Override
  public Boolean isNameExists(String name, Long id) {
    return getAll().stream()
        .anyMatch(category -> (category.getName().equals(name) && !category.getId().equals(id)));
  }

  @Override
  protected String getEntityName() {
    return "Category";
  }
}
