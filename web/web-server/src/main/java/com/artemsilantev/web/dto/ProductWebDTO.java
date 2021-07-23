package com.artemsilantev.web.dto;

import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductWebDTO {

  private Long id;
  private String name;
  private Collection<CategoryWebDTO> categories;
}
