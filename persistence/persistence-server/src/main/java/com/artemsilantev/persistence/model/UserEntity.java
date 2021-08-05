package com.artemsilantev.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_")
public class UserEntity extends PersistenceBaseEntity {

  @Column(name = "FIRST_NAME_")
  private String firstName;
  @Column(name = "SECOND_NAME_")
  private String secondName;
  @Column(name = "NUMBER_")
  private String number;
  @Column(name = "EMAIL_")
  private String email;
  @Column(name = "ADDRESS_")
  private String address;
}
