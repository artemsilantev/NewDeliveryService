package com.artemsilantev.persistence.mapper;

import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.Product;
import com.artemsilantev.persistence.model.ProductEntity;

@org.mapstruct.Mapper(componentModel = "spring", uses = CategoryEntityMapper.class)
public interface ProductEntityMapper extends Mapper<Product, ProductEntity> {

}
