package storages.impl;

import configs.DataStorageConfiguration;
import model.User;
import storages.UserDataStorage;

public class UserDataStorageImpl extends AbstractDataStorageImpl<User>
    implements UserDataStorage {

  public UserDataStorageImpl(DataStorageConfiguration configuration) {
    super(configuration);
  }


}