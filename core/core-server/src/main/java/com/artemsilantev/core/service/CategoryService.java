package com.artemsilantev.core.service;

import com.artemsilantev.core.dto.CategoryDto;
import com.artemsilantev.core.model.Category;
import java.util.Collection;


public interface CategoryService extends BaseService<CategoryDto, Category> {

  Collection<CategoryDto> getRootCategories();

  Collection<CategoryDto> getChildren(Long id);
}
