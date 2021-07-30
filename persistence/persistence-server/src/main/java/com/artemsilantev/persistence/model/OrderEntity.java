package com.artemsilantev.persistence.model;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "ORDER_")
public class OrderEntity extends PersistenceBaseEntity {

  @ManyToOne
  @JoinColumn(name = "FK_USER_ID_",referencedColumnName = "ID_")
  private UserEntity user;


  @ManyToMany
  @JoinTable(
      name = "ORDER_SHOP_ITEM_",
      joinColumns = {@JoinColumn(name = "ORDER_ID_")},
      inverseJoinColumns = {@JoinColumn(name = "SHOP_ITEM_ID_")}
  )
  private Collection<ShopItemEntity> items;
  @Column(name = "TOTAL_COST_")
  private Double totalCost;

}
