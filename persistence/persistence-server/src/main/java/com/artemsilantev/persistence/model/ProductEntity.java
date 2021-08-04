package com.artemsilantev.persistence.model;

import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "PRODUCT_")
public class ProductEntity extends PersistenceBaseEntity {

  @Column(name = "NAME_")
  private String name;
  @Column(name = "DESCRIPTION_")
  private String description;

  @ManyToMany
  @JoinTable(
      name = "PRODUCT_CATEGORY_",
      joinColumns = {@JoinColumn(name = "PRODUCT_ID_")},
      inverseJoinColumns = {@JoinColumn(name = "CATEGORY_ID_")}
  )
  private Collection<CategoryEntity> categories;

  @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  private Collection<ShopItemEntity> shopItems;
}
