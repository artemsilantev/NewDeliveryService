package com.artemsilantev.core.mappers.impl.dto;

import com.artemsilantev.core.dto.CategoryDTO;
import com.artemsilantev.core.mappers.Mapper;
import com.artemsilantev.core.model.Category;
import java.util.Collection;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class CategoryDTOMapper implements Mapper<CategoryDTO, Category> {

  private final ModelMapper modelMapper;

  public CategoryDTOMapper() {
    modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
    var map = modelMapper.createTypeMap(Category.class, CategoryDTO.class);
    map.addMapping(category -> {
      var parent = category.getParent();
      return parent != null ? parent.getId() : null;
    }, CategoryDTO::setParentId);
  }


  @Override
  public CategoryDTO toTarget(Category source) {
    return modelMapper.map(source,
        CategoryDTO.class);
  }

  @Override
  public Category toSource(CategoryDTO target) {
    return modelMapper.map(target, Category.class);
  }

  @Override
  public Collection<CategoryDTO> toTargetCollection(Collection<Category> sourceCollection) {
    return sourceCollection.stream()
        .map(this::toTarget)
        .collect(Collectors.toList());
  }

  @Override
  public Collection<Category> toSourceCollection(Collection<CategoryDTO> targetCollection) {
    return targetCollection.stream()
        .map(this::toSource)
        .collect(Collectors.toList());
  }
}
