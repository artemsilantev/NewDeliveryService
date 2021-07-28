package com.artemsilantev.web.mapper;

import com.artemsilantev.core.dto.ProductDto;
import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.web.dto.ProductWebDTO;
import com.artemsilantev.web.request.ProductCreateRequest;
import com.artemsilantev.web.request.ProductUpdateRequest;

@org.mapstruct.Mapper(componentModel = "spring", uses = {CategoryWebMapper.class})
public interface ProductWebMapper extends Mapper<ProductWebDTO, ProductDto> {

  ProductDto toSource(ProductCreateRequest target);

  ProductDto toSource(ProductUpdateRequest target);

}
