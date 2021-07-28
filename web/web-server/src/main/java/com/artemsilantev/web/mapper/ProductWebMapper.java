package com.artemsilantev.web.mapper;

import com.artemsilantev.core.dto.ProductDto;
import com.artemsilantev.web.request.ProductCreateRequest;
import com.artemsilantev.web.request.ProductUpdateRequest;

@org.mapstruct.Mapper(componentModel = "spring")
public interface ProductWebMapper{

  ProductDto toSource(ProductCreateRequest target);

  ProductDto toSource(ProductUpdateRequest target);

}
