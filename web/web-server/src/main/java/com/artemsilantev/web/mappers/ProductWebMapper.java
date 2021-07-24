package com.artemsilantev.web.mappers;

import com.artemsilantev.core.dto.ProductDTO;
import com.artemsilantev.core.mappers.Mapper;
import com.artemsilantev.web.dto.ProductWebDTO;
import com.artemsilantev.web.requests.ProductCreateRequest;
import com.artemsilantev.web.requests.ProductUpdateRequest;
import java.util.Collection;

@org.mapstruct.Mapper(componentModel = "spring")
public interface ProductWebMapper extends Mapper<ProductWebDTO, ProductDTO> {

  @Override
  ProductDTO toSource(ProductWebDTO target);

  ProductDTO toSource(ProductCreateRequest target);

  ProductDTO toSource(ProductUpdateRequest target);

  @Override
  Collection<ProductWebDTO> toTargetCollection(Collection<ProductDTO> sourceCollection);

  @Override
  Collection<ProductDTO> toSourceCollection(Collection<ProductWebDTO> targetCollection);

  @Override
  ProductWebDTO toTarget(ProductDTO source);
}
