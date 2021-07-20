package com.artemsilantev.core.services;

import com.artemsilantev.core.dto.CategoryDTO;
import com.artemsilantev.core.model.Category;
import java.util.Collection;


public interface CategoryService extends AbstractService<CategoryDTO, Category> {

  Collection<CategoryDTO> getParents(Long id);
}
