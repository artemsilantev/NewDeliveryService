package repositories.impl;

import model.User;
import repositories.UserRepository;
import storages.UserDataStorage;

public class UserRepositoryImpl extends AbstractRepositoryImpl<User>
    implements UserRepository {
    public UserRepositoryImpl(UserDataStorage userDataStorage) {
        super(userDataStorage);
    }
}
