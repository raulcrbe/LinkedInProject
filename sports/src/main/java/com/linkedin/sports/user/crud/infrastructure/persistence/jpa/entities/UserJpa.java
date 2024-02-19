package com.linkedin.sports.user.crud.infrastructure.persistence.jpa.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.Objects;
import java.util.UUID;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserJpa {

  @Id private UUID id;

  private String name;

  private String email;

  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    if (!super.equals(o)) return false;
    UserJpa userJpa = (UserJpa) o;
    return getId() != null && Objects.equals(getId(), userJpa.getId());
  }

  @Override
  public final int hashCode() {
    return Objects.hash(super.hashCode(), getName(), getEmail());
  }
}
