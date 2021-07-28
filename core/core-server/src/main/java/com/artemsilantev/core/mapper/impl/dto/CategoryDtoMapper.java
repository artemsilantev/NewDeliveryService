package com.artemsilantev.core.mapper.impl.dto;

import com.artemsilantev.core.dto.CategoryDto;
import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.Category;
import java.util.Collection;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class CategoryDtoMapper implements Mapper<CategoryDto, Category> {

  private final ModelMapper modelMapper;

  public CategoryDtoMapper() {
    modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
    var map = modelMapper.createTypeMap(Category.class, CategoryDto.class);
    map.addMapping(category -> {
      var parent = category.getParent();
      return parent != null ? parent.getId() : null;
    }, CategoryDto::setParentId);
  }


  @Override
  public CategoryDto toTarget(Category source) {
    return modelMapper.map(source,
        CategoryDto.class);
  }

  @Override
  public Category toSource(CategoryDto target) {
    return modelMapper.map(target, Category.class);
  }

  @Override
  public Collection<CategoryDto> toTargetCollection(Collection<Category> sourceCollection) {
    return sourceCollection.stream()
        .map(this::toTarget)
        .collect(Collectors.toList());
  }

  @Override
  public Collection<Category> toSourceCollection(Collection<CategoryDto> targetCollection) {
    return targetCollection.stream()
        .map(this::toSource)
        .collect(Collectors.toList());
  }
}
