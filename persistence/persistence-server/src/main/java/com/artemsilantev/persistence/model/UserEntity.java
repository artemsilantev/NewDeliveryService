package com.artemsilantev.persistence.model;

import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
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

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  private Collection<OrderEntity> orders;
}
