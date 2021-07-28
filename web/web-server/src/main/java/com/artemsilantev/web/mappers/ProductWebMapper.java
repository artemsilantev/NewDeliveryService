package com.artemsilantev.web.mappers;

import com.artemsilantev.core.dto.ProductDto;
import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.web.dto.ProductWebDTO;
import com.artemsilantev.web.requests.ProductCreateRequest;
import com.artemsilantev.web.requests.ProductUpdateRequest;

@org.mapstruct.Mapper(componentModel = "spring", uses = {CategoryWebMapper.class})
public interface ProductWebMapper extends Mapper<ProductWebDTO, ProductDto> {

  ProductDto toSource(ProductCreateRequest target);

  ProductDto toSource(ProductUpdateRequest target);

}
