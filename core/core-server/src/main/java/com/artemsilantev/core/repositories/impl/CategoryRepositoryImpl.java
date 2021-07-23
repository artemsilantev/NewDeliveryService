package com.artemsilantev.core.repositories.impl;

import com.artemsilantev.core.model.Category;
import com.artemsilantev.core.repositories.CategoryRepository;
import com.artemsilantev.core.storages.CategoryDataStorage;
import java.util.Collection;
import java.util.stream.Collectors;

public class CategoryRepositoryImpl extends AbstractRepositoryImpl<Category>
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
}
