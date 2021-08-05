package com.artemsilantev.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class  ShopItemEntity extends PersistenceBaseEntity {

  @ManyToOne
  @JoinColumn(name = "FK_SHOP_ID_", referencedColumnName = "ID_")
  private ShopEntity shop;

  @ManyToOne
  @JoinColumn(name = "FK_PRODUCT_ID_", referencedColumnName = "ID_")
  private ProductEntity product;

  @Column(name = "PRICE_")
  private Double price;
  @Column(name = "AMOUNT_")
  private Integer amount;
}
