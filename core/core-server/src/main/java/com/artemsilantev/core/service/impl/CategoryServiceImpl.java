package com.artemsilantev.core.service.impl;

import com.artemsilantev.core.dto.CategoryDto;
import com.artemsilantev.core.exception.IllegalEntityException;
import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.Category;
import com.artemsilantev.core.repository.CategoryRepository;
import com.artemsilantev.core.service.CategoryService;
import java.util.Collection;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class CategoryServiceImpl extends BaseServiceImpl<CategoryDto, Category>
    implements CategoryService {

  public CategoryServiceImpl(Mapper<CategoryDto, Category> mapper,
      CategoryRepository categoryRepository) {
    super(mapper, categoryRepository);
  }

  @Override
  public CategoryDto create(CategoryDto categoryDTO) {
    log.debug("Category will be created: {}", categoryDTO.toString());
    if (((CategoryRepository) baseRepository).isNameExists(categoryDTO.getName())) {
      throw new IllegalEntityException(
          String.format("Category with this name already exists: %s", categoryDTO.getName()));
    }
    return mapperDTO.toTarget(baseRepository.create(mappedToSource(categoryDTO)));
  }

  @Override
  public void update(CategoryDto categoryDTO) {
    log.debug("Category will be updated: {}", categoryDTO.toString());
    if (((CategoryRepository) baseRepository).isNameExists(categoryDTO.getName(),
        categoryDTO.getId())) {
      throw new IllegalEntityException(
          String.format("Category with this name already exists: %s", categoryDTO.getName()));
    }
    baseRepository.update(mappedToSource(categoryDTO));
  }

  @Override
  public Collection<CategoryDto> getRootCategories() {
    return ((CategoryRepository) baseRepository).getRootCategories().stream()
        .map(mapperDTO::toTarget)
        .collect(Collectors.toList());
  }

  @Override
  public Collection<CategoryDto> getChildren(Long id) {
    var parent = baseRepository.get(id);
    return ((CategoryRepository) baseRepository).getChildrenCategories().stream()
        .filter(category -> category.getParent().equals(parent))
        .map(mapperDTO::toTarget)
        .collect(Collectors.toList());
  }


  private Category mappedToSource(CategoryDto categoryDTO) {
    var category = mapperDTO.toSource(categoryDTO);
    var parentId = categoryDTO.getParentId();
    if (parentId != null) {
      category.setParent(baseRepository.get(parentId));
    }
    return category;
  }
}
