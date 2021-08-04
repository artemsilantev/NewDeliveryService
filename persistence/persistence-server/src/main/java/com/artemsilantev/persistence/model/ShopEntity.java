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
@Table(name = "SHOP_")
public class ShopEntity extends PersistenceBaseEntity {

  @Column(name = "NAME_")
  private String name;
  @Column(name = "ADDRESS_")
  private String address;
  @Column(name = "EMAIL_")
  private String email;

  @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  private Collection<ShopItemEntity> shopItems;
}
