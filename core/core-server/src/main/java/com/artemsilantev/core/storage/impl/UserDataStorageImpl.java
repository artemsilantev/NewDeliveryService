package com.artemsilantev.core.storage.impl;

import com.artemsilantev.core.filemanager.FileManager;
import com.artemsilantev.core.handler.Handler;
import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.User;
import com.artemsilantev.core.storage.UserDataStorage;
import com.artemsilantev.core.validator.Validator;
import java.util.Collection;

public class UserDataStorageImpl extends BaseDataStorageImpl<User>
    implements UserDataStorage {


  public UserDataStorageImpl(
      Handler<Mapper<User, String>, String> mapperHandler,
      FileManager fileManager, String pathToFile,
      Collection<Validator<String>> textValidators,
      Collection<Validator<User>> entityValidators) {
    super(mapperHandler, fileManager, pathToFile, textValidators, entityValidators);
  }
}