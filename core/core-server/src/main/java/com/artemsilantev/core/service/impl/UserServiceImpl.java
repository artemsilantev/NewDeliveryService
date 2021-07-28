package com.artemsilantev.core.service.impl;

import com.artemsilantev.core.dto.UserDto;
import com.artemsilantev.core.exception.IllegalEntityException;
import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.User;
import com.artemsilantev.core.repository.UserRepository;
import com.artemsilantev.core.service.UserService;

public class UserServiceImpl extends BaseServiceImpl<UserDto, User>
    implements UserService {

  public UserServiceImpl(Mapper<UserDto, User> mapper, UserRepository userRepository) {
    super(mapper, userRepository);
  }

  @Override
  public UserDto create(UserDto userDTO) {
    if (((UserRepository) baseRepository).isEmailExists(userDTO.getEmail())) {
      throw new IllegalEntityException(
          String.format("User with this email already exists: %s", userDTO.getEmail()));
    }
    return super.create(userDTO);
  }

  @Override
  public void update(UserDto userDTO) {
    if (((UserRepository) baseRepository).isEmailExists(userDTO.getEmail(), userDTO.getId())) {
      throw new IllegalEntityException(
          String.format("User with this email already exists: %s", userDTO.getEmail()));
    }
    super.update(userDTO);
  }
}
