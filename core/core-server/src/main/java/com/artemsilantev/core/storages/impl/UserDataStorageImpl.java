package com.artemsilantev.core.storages.impl;

import com.artemsilantev.core.filemanagers.FileManager;
import com.artemsilantev.core.handlers.Handler;
import com.artemsilantev.core.mappers.Mapper;
import com.artemsilantev.core.model.User;
import com.artemsilantev.core.storages.UserDataStorage;
import com.artemsilantev.core.validators.Validator;
import java.util.Collection;

public class UserDataStorageImpl extends AbstractDataStorageImpl<User>
    implements UserDataStorage {


  public UserDataStorageImpl(
      Handler<Mapper<User, String>, String> mapperHandler,
      FileManager fileManager, String pathToFile,
      Collection<Validator<String>> textValidators,
      Collection<Validator<User>> entityValidators) {
    super(mapperHandler, fileManager, pathToFile, textValidators, entityValidators);
  }
}