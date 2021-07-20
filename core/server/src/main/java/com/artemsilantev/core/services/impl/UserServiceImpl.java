package com.artemsilantev.core.services.impl;

import com.artemsilantev.core.dto.UserDTO;
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
  public UserDTO create(User entity) {
    for (User user : abstractRepository.getAll()) {
      if (user.getEmail().equals(entity.getEmail())) {
        entity.setId(user.getId());
        return mapperDTO.toTarget(entity);
      }
    }
    entity.setId(abstractRepository.create(entity).getId());
    return mapperDTO.toTarget(entity);
  }
}
