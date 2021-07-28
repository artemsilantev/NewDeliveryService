package com.artemsilantev.core.mapper.impl.json;

import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.Category;
import com.artemsilantev.core.util.GsonUtils;
import java.util.Collection;
import java.util.stream.Collectors;

public class CategoryJsonMapper implements Mapper<Category, String> {

  @Override
  public Category toTarget(String source) {
    return (Category) GsonUtils.deserialize(source, Category.class);
  }

  @Override
  public String toSource(Category target) {
    var parentOld = target.getParent();
    if (parentOld != null) {
      var parentNew = new Category();
      parentNew.setId(parentOld.getId());
      target.setParent(parentNew);
    }
    String result = GsonUtils.serialize(target);
    target.setParent(parentOld);
    return result;
  }

  @Override
  public Collection<Category> toTargetCollection(Collection<String> sourceCollection) {
    return sourceCollection.stream()
        .map(this::toTarget)
        .collect(Collectors.toList());
  }

  @Override
  public Collection<String> toSourceCollection(Collection<Category> targetCollection) {
    return targetCollection.stream()
        .map(this::toSource)
        .collect(Collectors.toList());
  }
}
