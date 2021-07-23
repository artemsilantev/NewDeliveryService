package com.artemsilantev.web.mappers;

import com.artemsilantev.core.dto.CategoryDTO;
import com.artemsilantev.core.mappers.Mapper;
import com.artemsilantev.web.dto.CategoryWebDTO;
import java.util.Collection;

@org.mapstruct.Mapper(componentModel = "spring")
public interface CategoryWebDTOMapper extends Mapper<CategoryWebDTO, CategoryDTO> {

  @Override
  CategoryDTO toSource(CategoryWebDTO target);

  @Override
  CategoryWebDTO toTarget(CategoryDTO source);

  @Override
  Collection<CategoryWebDTO> toTargetCollection(Collection<CategoryDTO> sourceCollection);
  @Override
  Collection<CategoryDTO> toSourceCollection(Collection<CategoryWebDTO> targetCollection);

}
