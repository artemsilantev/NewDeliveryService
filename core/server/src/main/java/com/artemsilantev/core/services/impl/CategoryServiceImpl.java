package com.artemsilantev.core.services.impl;

import com.artemsilantev.core.dto.CategoryDTO;
import com.artemsilantev.core.exceptions.IllegalEntityException;
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
  public CategoryDTO create(CategoryDTO categoryDTO) {
    for (Category category : abstractRepository.getAll()) {
      if (category.getName().equals(categoryDTO.getName())) {
        throw new IllegalEntityException(
            String.format("Category with this name already exists: %s", categoryDTO.getName()));
      }
    }
    return mapperDTO.toTarget(abstractRepository.create(mappedToSource(categoryDTO)));
  }

  @Override
  public void update(CategoryDTO categoryDTO) {
    for (Category category : abstractRepository.getAll()) {
      if (category.getName().equals(categoryDTO.getName())
          && !category.getId().equals(categoryDTO.getId())) {
        throw new IllegalEntityException(
            String.format("Category with this name already exists: %s", categoryDTO.getName()));
      }
    }
    abstractRepository.update(mappedToSource(categoryDTO));
  }

  @Override
  public Collection<CategoryDTO> getRootCategories() {
    return ((CategoryRepository) abstractRepository).getRootCategories().stream()
        .map(mapperDTO::toTarget)
        .collect(Collectors.toList());
  }

  @Override
  public Collection<CategoryDTO> getChildren(Long id) {
    var parent = abstractRepository.get(id);
    return ((CategoryRepository) abstractRepository).getCategoriesWithParent().stream()
        .filter(category -> category.getParent().equals(parent))
        .map(mapperDTO::toTarget)
        .collect(Collectors.toList());
  }


  private Category mappedToSource(CategoryDTO categoryDTO) {
    var category = mapperDTO.toSource(categoryDTO);
    var parentId = categoryDTO.getParentId();
    if (parentId != null) {
      category.setParent(abstractRepository.get(parentId));
    }
    return category;
  }
}
