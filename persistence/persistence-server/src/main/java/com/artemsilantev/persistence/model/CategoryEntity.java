package com.artemsilantev.persistence.model;

import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CATEGORY_")
public class CategoryEntity extends BaseEntity {

  @Column(name = "NAME_")
  private String name;

  @Column(name = "DESCRIPTION_")
  private String description;

  @ManyToOne
  private CategoryEntity parent;

  @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE)
  private Collection<CategoryEntity> children;
}
