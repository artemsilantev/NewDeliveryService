package com.artemsilantev.core.services;

import com.artemsilantev.core.dto.ProductDTO;
import com.artemsilantev.core.model.Product;

public interface ProductService extends AbstractService<ProductDTO, Product> {

  ProductDTO addCategory(Long productId, Long categoryId);
  ProductDTO removeCategory(Long productId, Long categoryId);
}
