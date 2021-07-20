package com.artemsilantev.core.services.impl;

import com.artemsilantev.core.dto.CategoryDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import com.artemsilantev.core.mappers.Mapper;
import com.artemsilantev.core.model.Category;
import com.artemsilantev.core.repositories.CategoryRepository;
import com.artemsilantev.core.services.CategoryService;

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
  public Collection<CategoryDTO> getParents(Long id) {
    var categories = new ArrayList<Category>();
    var startCategory = abstractRepository.get(id);
    var categoryCurrent = startCategory;
    while (categoryCurrent.getParent() != null && categoryCurrent.getParent() != startCategory) {
      categories.add(categoryCurrent.getParent());
      categoryCurrent = categoryCurrent.getParent();
    }
    return categories.stream()
        .map(mapperDTO::toTarget)
        .collect(Collectors.toList());
  }
}
