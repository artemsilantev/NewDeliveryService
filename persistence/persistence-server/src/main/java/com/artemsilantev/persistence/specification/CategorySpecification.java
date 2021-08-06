package com.artemsilantev.persistence.specification;

import com.artemsilantev.persistence.model.CategoryEntity;
import org.springframework.data.jpa.domain.Specification;

public class CategorySpecification {

  private CategorySpecification() {
  }

  public static Specification<CategoryEntity> nameStartWith(String name) {
    return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"),
        name + '%'));
  }

  public static Specification<CategoryEntity> parentIdEqual(Long id) {
    return (((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("parentId"),
        id)));
  }
}
