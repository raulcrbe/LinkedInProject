package com.linkedin.sports.user.crud.domain.models;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  private UUID id;
  private String name;
  private String email;
}
