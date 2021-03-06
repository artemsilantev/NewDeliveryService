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
@ToString(exclude = "id")
public class UserDto {

  private Long id;
  private String firstName;
  private String secondName;
  private String number;
  private String email;
  private String address;
}
