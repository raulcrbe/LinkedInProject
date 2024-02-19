package com.linkedin.sports.user.crud.application.create_user;

import com.linkedin.sports.user.crud.domain.models.User;
import com.linkedin.sports.user.crud.domain.ports.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserUseCase {

  private final UserRepository userRepository;

  public void createUser(User user) {
    userRepository.create(user);
  }
}
