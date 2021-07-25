package com.artemsilantev.web.requests;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryCreateRequest {

  @Size(min = 1, max = 64)
  @NotNull
  private String name;

  @Size(min = 1, max = 100)
  private String description;

  @Min(1)
  @Max(Long.MAX_VALUE)
  private Long parentId;
}
