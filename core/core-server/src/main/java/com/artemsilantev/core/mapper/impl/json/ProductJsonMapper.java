package com.artemsilantev.core.mapper.impl.json;

import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.Category;
import com.artemsilantev.core.model.Product;
import com.artemsilantev.core.util.GsonUtils;
import java.util.Collection;
import java.util.stream.Collectors;

public class ProductJsonMapper implements Mapper<Product, String> {

  @Override
  public Product toTarget(String source) {

    return (Product) GsonUtils.deserialize(source, Product.class);
  }

  @Override
  public String toSource(Product target) {
    var categoriesOld = target.getCategories();
    if (categoriesOld != null && !categoriesOld.isEmpty()) {
      target.setCategories(categoriesOld.stream()
          .map(categoryOld -> {
                var categoryNew = new Category();
                categoryNew.setId(categoryOld.getId());
                return categoryNew;
              }
          ).collect(Collectors.toList()));
    }
    String result = GsonUtils.serialize(target);
    target.setCategories(categoriesOld);
    return result;
  }

  @Override
  public Collection<Product> toTargetCollection(Collection<String> sourceCollection) {
    return sourceCollection.stream()
        .map(this::toTarget)
        .collect(Collectors.toList());
  }

  @Override
  public Collection<String> toSourceCollection(Collection<Product> targetCollection) {
    return targetCollection.stream()
        .map(this::toSource)
        .collect(Collectors.toList());
  }
}
