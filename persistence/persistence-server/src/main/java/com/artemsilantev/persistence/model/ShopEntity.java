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
@Table(name = "SHOP_")
public class ShopEntity extends PersistenceBaseEntity {

  @Column(name = "NAME_")
  private String name;
  @Column(name = "ADDRESS_")
  private String address;
  @Column(name = "EMAIL_")
  private String email;
}
