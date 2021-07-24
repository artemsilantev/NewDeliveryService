package com.artemsilantev.web.mappers;

import com.artemsilantev.core.dto.CategoryDTO;
import com.artemsilantev.core.mappers.Mapper;
import com.artemsilantev.web.dto.CategoryWebDTO;
import com.artemsilantev.web.requests.CategoryCreateRequest;
import com.artemsilantev.web.requests.CategoryUpdateRequest;
import java.util.Collection;

@org.mapstruct.Mapper(componentModel = "spring")
public interface CategoryWebMapper extends Mapper<CategoryWebDTO, CategoryDTO> {

  @Override
  CategoryDTO toSource(CategoryWebDTO target);

  CategoryDTO toSource(CategoryCreateRequest target);

  CategoryDTO toSource(CategoryUpdateRequest target);

  @Override
  CategoryWebDTO toTarget(CategoryDTO source);

  @Override
  Collection<CategoryWebDTO> toTargetCollection(Collection<CategoryDTO> sourceCollection);

  @Override
  Collection<CategoryDTO> toSourceCollection(Collection<CategoryWebDTO> targetCollection);

}
