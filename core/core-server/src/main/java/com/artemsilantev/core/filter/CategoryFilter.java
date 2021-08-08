package com.artemsilantev.core.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryFilter {

  @Nullable
  private String name;

  @Nullable
  private Long parentId;
}
