package com.artemsilantev.core.repositories.impl;

import com.artemsilantev.core.model.Category;
import com.artemsilantev.core.repositories.CategoryRepository;
import com.artemsilantev.core.storages.CategoryDataStorage;

public class CategoryRepositoryImpl extends AbstractRepositoryImpl<Category>
    implements CategoryRepository {

  public CategoryRepositoryImpl(CategoryDataStorage categoryDataStorage) {
    super(categoryDataStorage);
  }

}
