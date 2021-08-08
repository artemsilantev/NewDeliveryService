package com.artemsilantev.persistence.util;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;

public class PredicateBuilder<T> {

  private Predicate predicateFinal;
  private final CriteriaBuilder criteriaBuilder;

  public PredicateBuilder(
      CriteriaBuilder criteriaBuilder) {
    this.criteriaBuilder = criteriaBuilder;
  }

  public Predicate build() {
    return predicateFinal;
  }

  private PredicateBuilder<T> and(Predicate predicate) {
    if(predicateFinal==null){
      predicateFinal = criteriaBuilder.conjunction();
    }
    this.predicateFinal = criteriaBuilder.and(predicateFinal, predicate);
    return this;
  }

  public PredicateBuilder<T> equal(Path<?> attribute, Object criteria) {
    if (criteria == null) {
      return this;
    }
    var predicate = criteriaBuilder.equal(attribute, criteria);
    return and(predicate);
  }

  public PredicateBuilder<T> like(Path<String> attribute, String pattern) {
    if (StringUtils.isBlank(pattern)) {
      return this;
    }
    var predicate = criteriaBuilder.like(attribute, pattern);
    return and(predicate);
  }
}
