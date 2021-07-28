package com.artemsilantev.core.mapper.impl.dto;

import com.artemsilantev.core.dto.UserDto;
import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.User;
import java.util.Collection;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class UserDtoMapper implements Mapper<UserDto, User> {

  private final ModelMapper modelMapper;

  public UserDtoMapper() {
    modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
  }

  @Override
  public UserDto toTarget(User source) {
    return modelMapper.map(source,
        UserDto.class);
  }

  @Override
  public User toSource(UserDto target) {
    return modelMapper.map(target, User.class);
  }

  @Override
  public Collection<UserDto> toTargetCollection(Collection<User> sourceCollection) {
    return sourceCollection.stream()
        .map(this::toTarget)
        .collect(Collectors.toList());
  }

  @Override
  public Collection<User> toSourceCollection(Collection<UserDto> targetCollection) {
    return targetCollection.stream()
        .map(this::toSource)
        .collect(Collectors.toList());
  }

}
