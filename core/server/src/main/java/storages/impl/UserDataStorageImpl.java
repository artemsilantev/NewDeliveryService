package storages.impl;

import filemanagers.FileManager;
import handlers.Handler;
import java.util.Collection;
import mappers.Mapper;
import model.User;
import storages.UserDataStorage;
import validators.Validator;

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