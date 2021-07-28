package com.artemsilantev.core.model;

import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product extends BaseEntity {

  private String name;
  private String description;
  private Collection<Category> categories;
}
