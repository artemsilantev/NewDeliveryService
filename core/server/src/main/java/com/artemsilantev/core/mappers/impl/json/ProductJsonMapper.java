package com.artemsilantev.core.mappers.impl.json;

import java.util.stream.Collectors;
import com.artemsilantev.core.mappers.Mapper;
import com.artemsilantev.core.model.Category;
import com.artemsilantev.core.model.Product;
import com.artemsilantev.core.utils.GsonUtils;

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
}
