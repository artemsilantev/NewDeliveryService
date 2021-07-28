package com.artemsilantev.core.repository.impl;

import com.artemsilantev.core.model.User;
import com.artemsilantev.core.repository.UserRepository;
import com.artemsilantev.core.storage.UserDataStorage;

public class UserRepositoryImpl extends BaseRepositoryImpl<User>
    implements UserRepository {

  public UserRepositoryImpl(UserDataStorage userDataStorage) {
    super(userDataStorage);
  }

  @Override
  public Boolean isEmailExists(String name) {
    return getAll().stream()
        .anyMatch(user -> user.getEmail().equals(name));
  }

  @Override
  public Boolean isEmailExists(String name, Long id) {
    return getAll().stream()
        .anyMatch(user -> (user.getEmail().equals(name) && !user.getId().equals(id)));
  }

  @Override
  protected String getEntityName() {
    return "User";
  }
}
