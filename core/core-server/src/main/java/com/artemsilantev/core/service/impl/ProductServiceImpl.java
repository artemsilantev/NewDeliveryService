package com.artemsilantev.core.service.impl;

import com.artemsilantev.core.dto.CategoryDto;
import com.artemsilantev.core.dto.ProductDto;
import com.artemsilantev.core.exception.IllegalEntityException;
import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.Product;
import com.artemsilantev.core.repository.ProductRepository;
import com.artemsilantev.core.service.ProductService;


public class ProductServiceImpl extends BaseServiceImpl<ProductDto, Product>
    implements ProductService {

  public ProductServiceImpl(Mapper<ProductDto, Product> mapper,
      ProductRepository productRepository) {
    super(mapper, productRepository);
  }

  @Override
  public ProductDto create(ProductDto productDTO) {
    if (((ProductRepository) baseRepository).isNameExists(productDTO.getName())) {
      throw new IllegalEntityException(
          String.format("Product with this name already exists: %s", productDTO.getName()));
    }
    return super.create(productDTO);
  }

  @Override
  public void update(ProductDto productDTO) {
    if (((ProductRepository) baseRepository).isNameExists(productDTO.getName(),
        productDTO.getId())) {
      throw new IllegalEntityException(
          String.format("Product with this name already exists: %s", productDTO.getName()));
    }
    super.update(productDTO);
  }

  @Override
  public ProductDto addCategory(Long productId, Long categoryId) {
    var product = get(productId);
    product.getCategories().add(new CategoryDto(categoryId, null, null, null));
    update(product);
    return get(productId);
  }

  @Override
  public ProductDto removeCategory(Long productId, Long categoryId) {
    var product = get(productId);
    product.getCategories().removeIf(categoryDTO -> categoryDTO.getId().equals(categoryId));
    update(product);
    return get(productId);
  }
}
