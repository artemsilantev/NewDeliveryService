package com.artemsilantev.core.service;

import com.artemsilantev.core.dto.ProductDto;
import com.artemsilantev.core.model.Product;

public interface ProductService extends BaseService<ProductDto, Product> {

  ProductDto addCategory(Long productId, Long categoryId);
  ProductDto removeCategory(Long productId, Long categoryId);
}
