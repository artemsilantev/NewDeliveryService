package com.artemsilantev.persistence.specification;

import com.artemsilantev.core.filter.CategoryFilter;
import com.artemsilantev.persistence.model.CategoryEntity;
import com.artemsilantev.persistence.util.PredicateBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategorySpecification {

  public Specification<CategoryEntity> get(CategoryFilter filter) {
    return ((root, criteriaQuery, criteriaBuilder) -> {
      if (filter == null) {
        return null;
      }
      var builder = new PredicateBuilder<CategoryEntity>(criteriaBuilder);
      if (filter.getName() != null) {
        builder.like(root.get("name"), filter.getName() + "%");
      }
      if (filter.getParentId() != null) {
        builder.equal(root.get("parentId"), filter.getParentId());
      }
      return builder.build();
    });
  }
}
