package com.artemsilantev.core.mappers.impl.dto;

import com.artemsilantev.core.dto.CategoryDTO;
import com.artemsilantev.core.dto.ProductDTO;
import com.artemsilantev.core.mappers.Mapper;
import com.artemsilantev.core.model.Category;
import com.artemsilantev.core.model.Product;
import java.util.Collection;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class ProductDTOMapper implements Mapper<ProductDTO, Product> {

  private final ModelMapper mapper;

  public ProductDTOMapper() {
    mapper = new ModelMapper();
    mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    var map = mapper.createTypeMap(Category.class, CategoryDTO.class);
    map.addMapping(category -> {
      var parent = category.getParent();
      return parent != null ? parent.getId() : null;
    }, CategoryDTO::setParentId);
  }

  @Override
  public ProductDTO toTarget(Product product) {
    return mapper.map(product, ProductDTO.class);
  }

  @Override
  public Product toSource(ProductDTO productDTO) {
    return mapper.map(productDTO, Product.class);
  }

  @Override
  public Collection<ProductDTO> toTargetCollection(Collection<Product> sourceCollection) {
    return sourceCollection.stream()
        .map(this::toTarget)
        .collect(Collectors.toList());
  }

  @Override
  public Collection<Product> toSourceCollection(Collection<ProductDTO> targetCollection) {
    return targetCollection.stream()
        .map(this::toSource)
        .collect(Collectors.toList());
  }
}
