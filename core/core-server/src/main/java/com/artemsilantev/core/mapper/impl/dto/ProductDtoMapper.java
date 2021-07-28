package com.artemsilantev.core.mapper.impl.dto;

import com.artemsilantev.core.dto.CategoryDto;
import com.artemsilantev.core.dto.ProductDto;
import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.Category;
import com.artemsilantev.core.model.Product;
import java.util.Collection;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class ProductDtoMapper implements Mapper<ProductDto, Product> {

  private final ModelMapper mapper;

  public ProductDtoMapper() {
    mapper = new ModelMapper();
    mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    var map = mapper.createTypeMap(Category.class, CategoryDto.class);
    map.addMapping(category -> {
      var parent = category.getParent();
      return parent != null ? parent.getId() : null;
    }, CategoryDto::setParentId);
  }

  @Override
  public ProductDto toTarget(Product source) {
    return mapper.map(source, ProductDto.class);
  }

  @Override
  public Product toSource(ProductDto target) {
    return mapper.map(target, Product.class);
  }

  @Override
  public Collection<ProductDto> toTargetCollection(Collection<Product> sourceCollection) {
    return sourceCollection.stream()
        .map(this::toTarget)
        .collect(Collectors.toList());
  }

  @Override
  public Collection<Product> toSourceCollection(Collection<ProductDto> targetCollection) {
    return targetCollection.stream()
        .map(this::toSource)
        .collect(Collectors.toList());
  }
}
