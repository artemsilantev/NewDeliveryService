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
  public UserDto create(UserDto userDto) {
    if (((UserRepository) baseRepository).isEmailExists(userDto.getEmail())) {
      throw new IllegalEntityException(
          String.format("User with this email already exists: %s", userDto.getEmail()));
    }
    return super.create(userDto);
  }

  @Override
  public void update(UserDto userDto) {
    if (((UserRepository) baseRepository).isEmailExists(userDto.getEmail(), userDto.getId())) {
      throw new IllegalEntityException(
          String.format("User with this email already exists: %s", userDto.getEmail()));
    }
    super.update(userDto);
  }
}
