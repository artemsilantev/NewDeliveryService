package com.artemsilantev.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
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
@Table(name = "SHOP_ITEM_")
public class ShopItemEntity extends PersistenceBaseEntity {

  @OneToOne
  private ShopEntity shop;

  @OneToOne
  private ProductEntity product;

  @Column(name = "PRICE_")
  private Double price;
  @Column(name = "AMOUNT_")
  private Integer amount;
}
