package com.artemsilantev.web.mapper;

import com.artemsilantev.core.dto.CategoryDto;
import com.artemsilantev.web.request.CategoryCreateRequest;
import com.artemsilantev.web.request.CategoryUpdateRequest;

@org.mapstruct.Mapper(componentModel = "spring")
public interface CategoryWebMapper{

  CategoryDto toSource(CategoryCreateRequest target);

  CategoryDto toSource(CategoryUpdateRequest target);

}
