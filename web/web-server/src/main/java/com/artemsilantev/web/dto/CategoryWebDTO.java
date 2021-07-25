package com.artemsilantev.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryWebDTO {

  private Long id;
  private String name;
  private String description;
  private Long parentId;
}
