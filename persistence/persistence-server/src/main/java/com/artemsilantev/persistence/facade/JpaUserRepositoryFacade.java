package com.artemsilantev.persistence.facade;

import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.User;
import com.artemsilantev.core.repository.UserRepository;
import com.artemsilantev.persistence.model.UserEntity;
import com.artemsilantev.persistence.repository.JpaUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public class JpaUserRepositoryFacade extends JpaBaseRepositoryFacade<User, UserEntity>
    implements UserRepository {

  public JpaUserRepositoryFacade(
      JpaRepository<UserEntity, Long> repository,
      Mapper<User, UserEntity> mapper) {
    super(repository, mapper);
  }

  @Override
  @Transactional(readOnly = true)
  public Boolean isEmailExists(String email) {
    return ((JpaUserRepository) repository).existsByEmail(email);
  }

  @Override
  @Transactional(readOnly = true)
  public Boolean isEmailExists(String email, Long id) {
    return ((JpaUserRepository) repository).existsByEmailAndIdIsNot(email, id);
  }
}
