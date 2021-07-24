package com.artemsilantev.core.repositories.impl;

import com.artemsilantev.core.model.User;
import com.artemsilantev.core.repositories.UserRepository;
import com.artemsilantev.core.storages.UserDataStorage;

public class UserRepositoryImpl extends AbstractRepositoryImpl<User>
    implements UserRepository {

  public UserRepositoryImpl(UserDataStorage userDataStorage) {
    super(userDataStorage);
  }

  @Override
  protected String getEntityName() {
    return "User";
  }
}
