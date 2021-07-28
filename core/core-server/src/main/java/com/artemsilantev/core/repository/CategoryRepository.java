package com.artemsilantev.core.repository;

import com.artemsilantev.core.model.Category;
import java.util.Collection;

public interface CategoryRepository extends BaseRepository<Category> {

  Collection<Category> getRootCategories();

  Collection<Category> getChildrenCategories();

  Boolean isNameExists(String name);

  Boolean isNameExists(String name, Long id);
}
