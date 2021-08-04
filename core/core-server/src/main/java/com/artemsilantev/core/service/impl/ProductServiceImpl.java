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
  public ProductDto create(ProductDto productDto) {
    if (((ProductRepository) baseRepository).isNameExists(productDto.getName())) {
      throw new IllegalEntityException(
          String.format("Product with this name already exists: %s", productDto.getName()));
    }
    return super.create(productDto);
  }

  @Override
  public ProductDto update(ProductDto productDto) {
    if (((ProductRepository) baseRepository).isNameExists(productDto.getName(),
        productDto.getId())) {
      throw new IllegalEntityException(
          String.format("Product with this name already exists: %s", productDto.getName()));
    }
    return super.update(productDto);
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
    product.getCategories().removeIf(categoryDto -> categoryDto.getId().equals(categoryId));
    update(product);
    return get(productId);
  }
}
