package com.artemsilantev.core.validators.entity;

import com.artemsilantev.core.model.Product;
import com.artemsilantev.core.validators.Validator;
import java.util.ArrayList;
import java.util.Collection;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@AllArgsConstructor
public class ProductValidator implements Validator<Product> {

  @Override
  public Collection<String> validate(Product product) {
    var problems = new ArrayList<String>();
    problems.addAll(checkName(product.getName()));
    return problems;
  }

  private Collection<String> checkName(String name) {
    var problems = new ArrayList<String>();
    if (StringUtils.isBlank(name)) {
      problems.add("Name shouldn't be blank");
    }
    return problems;
  }
}
