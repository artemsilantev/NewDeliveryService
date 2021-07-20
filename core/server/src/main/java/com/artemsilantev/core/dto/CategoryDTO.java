package com.artemsilantev.core.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"id", "parentId"})
public class CategoryDTO {

  private Long id;
  private String name;
  private String description;
  private Long parentId;
}
