package com.artemsilantev.core.repositories;

import com.artemsilantev.core.model.Category;
import java.util.Collection;

public interface CategoryRepository extends AbstractRepository<Category> {

  Collection<Category> getRootCategories();

  Collection<Category> getCategoriesWithParent();
}
