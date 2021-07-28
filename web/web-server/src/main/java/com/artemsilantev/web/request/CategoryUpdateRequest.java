package com.artemsilantev.web.request;

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
public class CategoryUpdateRequest {

  @NotNull
  @Min(1)
  @Max(value = Long.MAX_VALUE)
  private Long id;

  @Size(max = 64)
  private String name;

  @Size(max = 64)
  private String description;

  @Min(1)
  @Max(Long.MAX_VALUE)
  private Long parentId;
}
