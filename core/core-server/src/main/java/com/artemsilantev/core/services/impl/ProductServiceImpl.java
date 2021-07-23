package com.artemsilantev.core.services.impl;

import com.artemsilantev.core.dto.ProductDTO;
import com.artemsilantev.core.exceptions.IllegalEntityException;
import com.artemsilantev.core.mappers.Mapper;
import com.artemsilantev.core.model.Product;
import com.artemsilantev.core.repositories.ProductRepository;
import com.artemsilantev.core.services.ProductService;

public class ProductServiceImpl extends AbstractServiceImpl<ProductDTO, Product>
    implements ProductService {

  public ProductServiceImpl(Mapper<ProductDTO, Product> mapper,
      ProductRepository productRepository) {
    super(mapper, productRepository);
  }

  @Override
  public ProductDTO create(ProductDTO productDTO) {
    for (Product product : abstractRepository.getAll()) {
      if (product.getName().equals(productDTO.getName())) {
        throw new IllegalEntityException(
            String.format("Product with this name already exists: %s", productDTO.getName()));
      }
    }
    return super.create(productDTO);
  }

  @Override
  public void update(ProductDTO productDTO) {
    for (Product product : abstractRepository.getAll()) {
      if (product.getName().equals(productDTO.getName())
          && !product.getId().equals(productDTO.getId())) {
        throw new IllegalEntityException(
            String.format("Product with this name already exists: %s", productDTO.getName()));
      }
    }
    super.update(productDTO);
  }
}
