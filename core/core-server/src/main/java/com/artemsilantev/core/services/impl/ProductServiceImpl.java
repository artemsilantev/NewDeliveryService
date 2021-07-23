package com.artemsilantev.core.services.impl;

import com.artemsilantev.core.dto.CategoryDTO;
import com.artemsilantev.core.dto.ProductDTO;
import com.artemsilantev.core.exceptions.IllegalEntityException;
import com.artemsilantev.core.mappers.Mapper;
import com.artemsilantev.core.model.Product;
import com.artemsilantev.core.repositories.ProductRepository;
import com.artemsilantev.core.services.CategoryService;
import com.artemsilantev.core.services.ProductService;
import java.util.Collection;
import java.util.stream.Collectors;


public class ProductServiceImpl extends AbstractServiceImpl<ProductDTO, Product>
    implements ProductService {

  private CategoryService categoryService;

  public ProductServiceImpl(Mapper<ProductDTO, Product> mapper,
      ProductRepository productRepository,
      CategoryService categoryService) {
    super(mapper, productRepository);
    this.categoryService = categoryService;
  }

  @Override
  public ProductDTO create(ProductDTO productDTO) {
    for (Product product : abstractRepository.getAll()) {
      if (product.getName().equals(productDTO.getName())) {
        throw new IllegalEntityException(
            String.format("Product with this name already exists: %s", productDTO.getName()));
      }
    }
    productDTO.setCategories(fillCategories(productDTO.getCategories()));
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
    productDTO.setCategories(fillCategories(productDTO.getCategories()));
    super.update(productDTO);
  }

  private Collection<CategoryDTO> fillCategories(Collection<CategoryDTO> categories){
    return categories.stream()
        .map(categoryDTO -> categoryService.get(categoryDTO.getId()))
        .collect(Collectors.toList());

  }
}
