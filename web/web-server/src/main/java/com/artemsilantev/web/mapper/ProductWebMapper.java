package com.artemsilantev.web.mapper;

import com.artemsilantev.core.dto.ProductDto;
import com.artemsilantev.web.request.ProductCreateRequest;
import com.artemsilantev.web.request.ProductPatchRequest;
import com.artemsilantev.web.request.ProductUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@org.mapstruct.Mapper(componentModel = "spring")
public interface ProductWebMapper{

  ProductDto toSourceCreate(ProductCreateRequest target);

  ProductDto toSourceUpdate(ProductUpdateRequest target);

  ProductDto toSourcePatch(ProductPatchRequest target);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
  @Mapping(target = "id", ignore = true)
  ProductDto patch(@MappingTarget ProductDto targetOld, ProductDto targetNew);

}
