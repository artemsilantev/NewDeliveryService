package com.artemsilantev.persistence.model;

import com.artemsilantev.persistence.listener.EntityAuditTrailsListener;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@EntityListeners(EntityAuditTrailsListener.class)
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
}
