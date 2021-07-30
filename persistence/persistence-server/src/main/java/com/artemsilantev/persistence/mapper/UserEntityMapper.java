package com.artemsilantev.persistence.mapper;

import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.User;
import com.artemsilantev.persistence.model.UserEntity;

@org.mapstruct.Mapper(componentModel = "spring")
public interface UserEntityMapper extends Mapper<User, UserEntity> {

}
