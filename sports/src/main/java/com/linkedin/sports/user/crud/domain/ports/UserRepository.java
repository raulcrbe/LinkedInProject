package com.linkedin.sports.user.crud.domain.ports;

import com.linkedin.sports.user.crud.domain.models.User;
import java.util.UUID;

public interface UserRepository {
  User findUser(UUID id);
}
