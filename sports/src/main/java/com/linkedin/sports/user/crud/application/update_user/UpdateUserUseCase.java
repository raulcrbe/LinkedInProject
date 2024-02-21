package com.linkedin.sports.user.crud.application.update_user;

import com.linkedin.sports.user.crud.domain.models.User;
import com.linkedin.sports.user.crud.domain.ports.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserUseCase {

  private final UserRepository userRepository;

  public void updateUser(User user) {
    userRepository.update(user);
  }
}
