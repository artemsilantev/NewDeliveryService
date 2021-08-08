package com.artemsilantev.core.service;

import com.artemsilantev.core.dto.CategoryDto;
import com.artemsilantev.core.filter.CategoryFilter;
import com.artemsilantev.core.model.Category;
import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CategoryService extends BaseService<CategoryDto, Category> {

  Collection<CategoryDto> getRootCategories();

  Collection<CategoryDto> getChildren(Long id);

  Page<CategoryDto> search(CategoryFilter filter, Pageable pageable);
}
