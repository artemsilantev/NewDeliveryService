package com.artemsilantev.core.mappers.impl.json;

import com.artemsilantev.core.mappers.Mapper;
import com.artemsilantev.core.model.User;
import com.artemsilantev.core.utils.GsonUtils;

public class UserJsonMapper implements Mapper<User, String> {

  @Override
  public User toTarget(String source) {
    return (User) GsonUtils.deserialize(source, User.class);
  }

  @Override
  public String toSource(User target) {
    return GsonUtils.serialize(target);
  }
}
