package com.artemsilantev.web.mappers;

import com.artemsilantev.core.dto.CategoryDTO;
import com.artemsilantev.core.mappers.Mapper;
import com.artemsilantev.web.dto.CategoryWebDTO;
import com.artemsilantev.web.requests.CategoryCreateRequest;
import com.artemsilantev.web.requests.CategoryUpdateRequest;

@org.mapstruct.Mapper(componentModel = "spring")
public interface CategoryWebMapper extends Mapper<CategoryWebDTO, CategoryDTO> {

  CategoryDTO toSource(CategoryCreateRequest target);

  CategoryDTO toSource(CategoryUpdateRequest target);

}
