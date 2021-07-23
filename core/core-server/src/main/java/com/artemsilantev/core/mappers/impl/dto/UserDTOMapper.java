package com.artemsilantev.core.mappers.impl.dto;

import com.artemsilantev.core.dto.UserDTO;
import com.artemsilantev.core.mappers.Mapper;
import com.artemsilantev.core.model.User;
import java.util.Collection;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class UserDTOMapper implements Mapper<UserDTO, User> {

  private final ModelMapper modelMapper;

  public UserDTOMapper() {
    modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
  }

  @Override
  public UserDTO toTarget(User source) {
    return modelMapper.map(source,
        UserDTO.class);
  }

  @Override
  public User toSource(UserDTO target) {
    return modelMapper.map(target, User.class);
  }

  @Override
  public Collection<UserDTO> toTargetCollection(Collection<User> sourceCollection) {
    return sourceCollection.stream()
        .map(this::toTarget)
        .collect(Collectors.toList());
  }

  @Override
  public Collection<User> toSourceCollection(Collection<UserDTO> targetCollection) {
    return targetCollection.stream()
        .map(this::toSource)
        .collect(Collectors.toList());
  }

}
