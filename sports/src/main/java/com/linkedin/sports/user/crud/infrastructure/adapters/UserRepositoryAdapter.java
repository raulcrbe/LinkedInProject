package com.linkedin.sports.user.crud.infrastructure.adapters;

import com.linkedin.sports.user.crud.domain.models.User;
import com.linkedin.sports.user.crud.domain.ports.UserRepository;
import com.linkedin.sports.user.crud.infrastructure.persistence.jpa.entities.UserJpa;
import com.linkedin.sports.user.crud.infrastructure.persistence.jpa.repositories.UserRepositoryJpa;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

  private final UserRepositoryJpa userRepositoryJpa;

  @Override
  public User findById(UUID id) {
    Optional<UserJpa> user = userRepositoryJpa.findById(id);
    return user.map(userJpa -> new User(id, userJpa.getName(), userJpa.getEmail()))
        .orElseGet(() -> new User(id, "test", "test"));
  }

  @Override
  public void create(User user) {
    userRepositoryJpa.save(new UserJpa(user.getId(), user.getName(), user.getEmail()));
  }
}
