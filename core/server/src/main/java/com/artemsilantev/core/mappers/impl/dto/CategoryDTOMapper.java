package com.artemsilantev.core.mappers.impl.dto;

import com.artemsilantev.core.dto.CategoryDTO;
import com.artemsilantev.core.mappers.Mapper;
import com.artemsilantev.core.model.Category;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class CategoryDTOMapper implements Mapper<CategoryDTO, Category> {

  private final ModelMapper modelMapper;

  public CategoryDTOMapper() {
    modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
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
}
