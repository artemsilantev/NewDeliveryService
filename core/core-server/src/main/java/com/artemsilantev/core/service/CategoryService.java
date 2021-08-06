package com.artemsilantev.core.service;

import com.artemsilantev.core.dto.CategoryDto;
import com.artemsilantev.core.model.Category;
import java.util.Collection;
import org.springframework.data.domain.Sort;


public interface CategoryService extends BaseService<CategoryDto, Category> {

  Collection<CategoryDto> getRootCategories();

  Collection<CategoryDto> getChildren(Long id);

  Collection<CategoryDto> getNameStartWith(String name, Sort sort);

  Collection<CategoryDto> getNameStartWithAndParent(String name, Long parentId, Sort sort);
}
