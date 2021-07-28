package com.artemsilantev.core.mapper.impl.json;

import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.Shop;
import com.artemsilantev.core.util.GsonUtils;
import java.util.Collection;
import java.util.stream.Collectors;

public class ShopJsonMapper implements Mapper<Shop, String> {

  @Override
  public Shop toTarget(String source) {
    return (Shop) GsonUtils.deserialize(source, Shop.class);
  }

  @Override
  public String toSource(Shop target) {
    return GsonUtils.serialize(target);
  }

  @Override
  public Collection<Shop> toTargetCollection(Collection<String> sourceCollection) {
    return sourceCollection.stream()
        .map(this::toTarget)
        .collect(Collectors.toList());
  }

  @Override
  public Collection<String> toSourceCollection(Collection<Shop> targetCollection) {
    return targetCollection.stream()
        .map(this::toSource)
        .collect(Collectors.toList());
  }
}
