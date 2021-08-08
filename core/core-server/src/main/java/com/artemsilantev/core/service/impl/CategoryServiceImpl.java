package com.artemsilantev.core.service.impl;

import com.artemsilantev.core.dto.CategoryDto;
import com.artemsilantev.core.exception.IllegalEntityException;
import com.artemsilantev.core.filter.CategoryFilter;
import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.Category;
import com.artemsilantev.core.repository.CategoryRepository;
import com.artemsilantev.core.service.CategoryService;
import java.util.Collection;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


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
  public CategoryDto update(CategoryDto categoryDto) {
    log.debug("Category will be updated: {}", categoryDto.toString());
    if (((CategoryRepository) baseRepository).isNameExists(categoryDto.getName(),
        categoryDto.getId())) {
      throw new IllegalEntityException(
          String.format("Category with this name already exists: %s", categoryDto.getName()));
    }
    return mapperDto.toTarget(baseRepository.update(mappedToSource(categoryDto)));
  }

  @Override
  public Collection<CategoryDto> getRootCategories() {
    return mapperDto.toTargetCollection(((CategoryRepository) baseRepository).getRootCategories());
  }

  @Override
  public Collection<CategoryDto> getChildren(Long id) {
    var parent = baseRepository.get(id);
    return ((CategoryRepository) baseRepository).getChildrenCategories().stream()
        .filter(category -> category.getParent().equals(parent))
        .map(mapperDto::toTarget)
        .collect(Collectors.toList());
  }

  @Override
  public Page<CategoryDto> search(CategoryFilter filter, Pageable pageable) {
    return ((CategoryRepository) baseRepository).search(filter, pageable).map(mapperDto::toTarget);
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
