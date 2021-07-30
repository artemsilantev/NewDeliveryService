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
  public CategoryDto create(CategoryDto categoryDto) {
    log.debug("Category will be created: {}", categoryDto.toString());
    if (((CategoryRepository) baseRepository).isNameExists(categoryDto.getName())) {
      throw new IllegalEntityException(
          String.format("Category with this name already exists: %s", categoryDto.getName()));
    }
    return mapperDto.toTarget(baseRepository.create(mappedToSource(categoryDto)));
  }

  @Override
  public void update(CategoryDto categoryDto) {
    log.debug("Category will be updated: {}", categoryDto.toString());
    if (((CategoryRepository) baseRepository).isNameExists(categoryDto.getName(),
        categoryDto.getId())) {
      throw new IllegalEntityException(
          String.format("Category with this name already exists: %s", categoryDto.getName()));
    }
    baseRepository.update(mappedToSource(categoryDto));
  }

  @Override
  public Collection<CategoryDto> getRootCategories() {
    return ((CategoryRepository) baseRepository).getRootCategories().stream()
        .map(mapperDto::toTarget)
        .collect(Collectors.toList());
  }

  @Override
  public Collection<CategoryDto> getChildren(Long id) {
    var parent = baseRepository.get(id);
    return ((CategoryRepository) baseRepository).getChildrenCategories().stream()
        .filter(category -> category.getParent().equals(parent))
        .map(mapperDto::toTarget)
        .collect(Collectors.toList());
  }


  private Category mappedToSource(CategoryDto categoryDto) {
    var category = mapperDto.toSource(categoryDto);
    var parentId = categoryDto.getParentId();
    if (parentId != null) {
      category.setParent(baseRepository.get(parentId));
    }
    return category;
  }
}
