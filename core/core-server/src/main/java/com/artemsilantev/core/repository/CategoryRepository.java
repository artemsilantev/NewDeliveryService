package com.artemsilantev.core.repository;

import com.artemsilantev.core.filter.CategoryFilter;
import com.artemsilantev.core.model.Category;
import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryRepository extends BaseRepository<Category> {

  Collection<Category> getRootCategories();

  Collection<Category> getChildrenCategories();

  Page<Category> search(CategoryFilter filter, Pageable pageable);

  Boolean isNameExists(String name);

  Boolean isNameExists(String name, Long id);
}
