package com.artemsilantev.core.dto;

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
@ToString(exclude = "id")
public class ProductDto {

  private Long id;
  private String name;
  private String description;
  private Collection<CategoryDto> categories;
}
