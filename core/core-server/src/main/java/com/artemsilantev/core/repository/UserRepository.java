package com.artemsilantev.core.repository;

import com.artemsilantev.core.model.User;

public interface UserRepository extends BaseRepository<User> {

  Boolean isEmailExists(String name);

  Boolean isEmailExists(String name, Long id);
}
