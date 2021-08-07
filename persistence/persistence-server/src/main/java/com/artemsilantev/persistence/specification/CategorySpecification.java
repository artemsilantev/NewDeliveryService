package com.artemsilantev.persistence.specification;

import com.artemsilantev.persistence.model.CategoryEntity;
import org.springframework.data.jpa.domain.Specification;

public class CategorySpecification {

  private CategorySpecification() {
  }

  public static Specification<CategoryEntity> nameStartWith(String name) {
    return ((root, criteriaQuery, criteriaBuilder) -> {
      if (name == null) {
        return criteriaBuilder.conjunction();
      }
      return criteriaBuilder.like(root.get("name"),
          name + '%');
    });
  }

  public static Specification<CategoryEntity> parentIdEqual(Long id) {
    return (((root, criteriaQuery, criteriaBuilder) -> {
      if (id == null) {
        return criteriaBuilder.conjunction();
      }
      return criteriaBuilder.equal(root.get("parentId"),
          id);
    }));
  }
}
