package com.artemsilantev.web.mapper;

import com.artemsilantev.core.dto.CategoryDto;
import com.artemsilantev.web.request.CategoryCreateRequest;
import com.artemsilantev.web.request.CategoryPatchRequest;
import com.artemsilantev.web.request.CategoryUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@org.mapstruct.Mapper(componentModel = "spring")
public interface CategoryWebMapper {

  CategoryDto toSourceCreate(CategoryCreateRequest target);

  CategoryDto toSourceUpdate(CategoryUpdateRequest target);

  CategoryDto toSourcePatch(CategoryPatchRequest target);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
  @Mapping(target = "id", ignore = true)
  CategoryDto patch(@MappingTarget CategoryDto targetOld, CategoryDto targetNew);

}
