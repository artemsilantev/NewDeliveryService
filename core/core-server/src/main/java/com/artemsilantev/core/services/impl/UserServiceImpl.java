package com.artemsilantev.core.services.impl;

import com.artemsilantev.core.dto.UserDTO;
import com.artemsilantev.core.exceptions.IllegalEntityException;
import com.artemsilantev.core.mappers.Mapper;
import com.artemsilantev.core.model.User;
import com.artemsilantev.core.repositories.UserRepository;
import com.artemsilantev.core.services.UserService;

public class UserServiceImpl extends AbstractServiceImpl<UserDTO, User>
    implements UserService {

  public UserServiceImpl(Mapper<UserDTO, User> mapper, UserRepository userRepository) {
    super(mapper, userRepository);
  }

  @Override
  public UserDTO create(UserDTO userDTO) {
    for (User user : abstractRepository.getAll()) {
      if (user.getEmail().equals(userDTO.getEmail())) {
        throw new IllegalEntityException(
            String.format("User with this email already exists: %s", userDTO.getEmail()));
      }
    }
    return super.create(userDTO);
  }

  @Override
  public void update(UserDTO userDTO) {
    for (User user : abstractRepository.getAll()) {
      if (user.getEmail().equals(userDTO.getEmail())
          && !user.getId().equals(userDTO.getId())) {
        throw new IllegalEntityException(
            String.format("User with this email already exists: %s", userDTO.getEmail()));
      }
    }
    super.update(userDTO);
  }
}
