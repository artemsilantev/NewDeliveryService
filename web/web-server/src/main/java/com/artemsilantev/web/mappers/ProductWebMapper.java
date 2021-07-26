package com.artemsilantev.web.mappers;

import com.artemsilantev.core.dto.ProductDTO;
import com.artemsilantev.core.mappers.Mapper;
import com.artemsilantev.web.dto.ProductWebDTO;
import com.artemsilantev.web.requests.ProductCreateRequest;
import com.artemsilantev.web.requests.ProductUpdateRequest;

@org.mapstruct.Mapper(componentModel = "spring", uses = {CategoryWebMapper.class})
public interface ProductWebMapper extends Mapper<ProductWebDTO, ProductDTO> {

  ProductDTO toSource(ProductCreateRequest target);

  ProductDTO toSource(ProductUpdateRequest target);

}
