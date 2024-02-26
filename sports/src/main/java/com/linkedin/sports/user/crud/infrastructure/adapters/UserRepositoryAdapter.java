package com.linkedin.sports.user.crud.infrastructure.adapters;

import com.linkedin.sports.shared_kernel.domain.exceptions.ApplicationException;
import com.linkedin.sports.user.crud.domain.UserError;
import com.linkedin.sports.user.crud.domain.models.User;
import com.linkedin.sports.user.crud.domain.ports.UserRepository;
import com.linkedin.sports.user.crud.infrastructure.persistence.jpa.entities.UserJpa;
import com.linkedin.sports.user.crud.infrastructure.persistence.jpa.repositories.UserRepositoryJpa;
import java.util.Map;
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
        .orElseGet(User::new);
  }

  @Override
  public void create(User user) {
    userRepositoryJpa.save(new UserJpa(user.getId(), user.getName(), user.getEmail()));
  }

  @Override
  public void update(User user) {
    UserJpa userJpa = getUserJpa(user.getId());
    userJpa.setEmail(user.getEmail());
    userJpa.setName(user.getName());
    userRepositoryJpa.save(userJpa);
  }

  private UserJpa getUserJpa(UUID uuid) {
    Optional<UserJpa> userJpa = userRepositoryJpa.findById(uuid);
    if (userJpa.isEmpty())
      throw new ApplicationException(UserError.USER_NOT_FOUND, Map.of("id", uuid));
    return userJpa.get();
  }

  @Override
  public void delete(UUID uuid) {
    getUserJpa(uuid);
    userRepositoryJpa.deleteById(uuid);
  }
}
