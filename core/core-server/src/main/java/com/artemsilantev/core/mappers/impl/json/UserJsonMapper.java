package com.artemsilantev.core.mappers.impl.json;

import com.artemsilantev.core.mappers.Mapper;
import com.artemsilantev.core.model.User;
import com.artemsilantev.core.utils.GsonUtils;
import java.util.Collection;
import java.util.stream.Collectors;

public class UserJsonMapper implements Mapper<User, String> {

  @Override
  public User toTarget(String source) {
    return (User) GsonUtils.deserialize(source, User.class);
  }

  @Override
  public String toSource(User target) {
    return GsonUtils.serialize(target);
  }

  @Override
  public Collection<User> toTargetCollection(Collection<String> sourceCollection) {
    return sourceCollection.stream()
        .map(this::toTarget)
        .collect(Collectors.toList());
  }

  @Override
  public Collection<String> toSourceCollection(Collection<User> targetCollection) {
    return targetCollection.stream()
        .map(this::toSource)
        .collect(Collectors.toList());
  }
}
