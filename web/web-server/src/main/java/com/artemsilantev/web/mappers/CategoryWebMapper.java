package com.artemsilantev.web.mappers;

import com.artemsilantev.core.dto.CategoryDto;
import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.web.dto.CategoryWebDTO;
import com.artemsilantev.web.requests.CategoryCreateRequest;
import com.artemsilantev.web.requests.CategoryUpdateRequest;

@org.mapstruct.Mapper(componentModel = "spring")
public interface CategoryWebMapper extends Mapper<CategoryWebDTO, CategoryDto> {

  CategoryDto toSource(CategoryCreateRequest target);

  CategoryDto toSource(CategoryUpdateRequest target);

}
