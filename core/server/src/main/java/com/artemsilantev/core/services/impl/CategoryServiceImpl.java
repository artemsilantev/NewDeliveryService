package com.artemsilantev.core.services.impl;

import com.artemsilantev.core.dto.CategoryDTO;
import com.artemsilantev.core.mappers.Mapper;
import com.artemsilantev.core.model.Category;
import com.artemsilantev.core.repositories.CategoryRepository;
import com.artemsilantev.core.services.CategoryService;
import java.util.Collection;
import java.util.stream.Collectors;

public class CategoryServiceImpl extends AbstractServiceImpl<CategoryDTO, Category>
    implements CategoryService {

  public CategoryServiceImpl(Mapper<CategoryDTO, Category> mapper,
      CategoryRepository categoryRepository) {
    super(mapper, categoryRepository);
  }

  @Override
  public CategoryDTO create(Category entity) {
    for (Category category : abstractRepository.getAll()) {
      if (category.getName().equals(entity.getName())) {
        entity.setId(category.getId());
        return mapperDTO.toTarget(entity);
      }
    }
    entity.setId(abstractRepository.create(entity).getId());
    return mapperDTO.toTarget(entity);
  }


  @Override
  public Collection<CategoryDTO> getRootCategories() {
    return ((CategoryRepository) abstractRepository).getRootCategories().stream()
        .map(mapperDTO::toTarget)
        .collect(Collectors.toList());
  }

  @Override
  public Collection<CategoryDTO> getChildrenCategories(Long id) {
    var parent = abstractRepository.get(id);
    return ((CategoryRepository) abstractRepository).getCategoriesWithParent().stream()
        .filter(category -> category.getParent().equals(parent))
        .map(mapperDTO::toTarget)
        .collect(Collectors.toList());
  }

}
