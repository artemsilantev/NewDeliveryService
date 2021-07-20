package com.artemsilantev.core.mappers.impl.json;

import com.artemsilantev.core.mappers.Mapper;
import com.artemsilantev.core.model.Shop;
import com.artemsilantev.core.utils.GsonUtils;

public class ShopJsonMapper implements Mapper<Shop, String> {

  @Override
  public Shop toTarget(String source) {
    return (Shop) GsonUtils.deserialize(source, Shop.class);
  }

  @Override
  public String toSource(Shop target) {
    return GsonUtils.serialize(target);
  }
}
