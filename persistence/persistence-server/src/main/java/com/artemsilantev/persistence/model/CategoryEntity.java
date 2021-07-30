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
@Table(name = "CATEGORY_")
public class CategoryEntity extends PersistenceBaseEntity {

  @Column(name = "NAME_")
  private String name;

  @Column(name = "DESCRIPTION_")
  private String description;

  @ManyToOne
  @JoinColumn(name = "FK_CATEGORY_ID", referencedColumnName = "ID_")
  private CategoryEntity parent;

}