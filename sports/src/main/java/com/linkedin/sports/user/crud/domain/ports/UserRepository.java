package com.linkedin.sports.user.crud.domain.ports;

import com.linkedin.sports.user.crud.domain.models.User;
import java.util.UUID;

public interface UserRepository {
  User findById(UUID id);

  void create(User user);

  void update(User user);

  void delete(UUID id);
}
