package com.linkedin.sports.user.crud.infrastructure.adapters;

import com.linkedin.sports.user.crud.domain.models.User;
import com.linkedin.sports.user.crud.domain.ports.UserRepository;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class UserAdapter implements UserRepository {
  @Override
  public User findUser(UUID id) {
    return new User(id, "test", "test");
  }
}
