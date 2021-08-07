package com.artemsilantev.core.repository;

import com.artemsilantev.core.model.Category;
import java.util.Collection;
import org.springframework.data.domain.Sort;

public interface CategoryRepository extends BaseRepository<Category> {

  Collection<Category> getRootCategories();

  Collection<Category> getChildrenCategories();

  Collection<Category> search(String name, Long parentId, Sort sort);

  Boolean isNameExists(String name);

  Boolean isNameExists(String name, Long id);
}
