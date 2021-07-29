package com.artemsilantev.persistence.mapper;

import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.Category;
import com.artemsilantev.persistence.model.CategoryEntity;
@org.mapstruct.Mapper(componentModel = "spring")
public interface CategoryEntityMapper extends Mapper<Category, CategoryEntity> {
}
