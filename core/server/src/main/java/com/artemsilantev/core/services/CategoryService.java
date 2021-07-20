package com.artemsilantev.core.services;

import com.artemsilantev.core.dto.CategoryDTO;
import java.util.Collection;
import com.artemsilantev.core.model.Category;


public interface CategoryService extends AbstractService<CategoryDTO, Category> {

  Collection<CategoryDTO> getParents(Long id);
}
