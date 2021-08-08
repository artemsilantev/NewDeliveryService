package com.artemsilantev.core.repository.impl;

import com.artemsilantev.core.filter.CategoryFilter;
import com.artemsilantev.core.model.Category;
import com.artemsilantev.core.repository.CategoryRepository;
import com.artemsilantev.core.storage.CategoryDataStorage;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
  public Page<Category> search(CategoryFilter filter, Pageable pageable) {
    return Page.empty(pageable);
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
