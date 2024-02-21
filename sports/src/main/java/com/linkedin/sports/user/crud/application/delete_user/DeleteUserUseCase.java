package com.linkedin.sports.user.crud.application.delete_user;

import com.linkedin.sports.user.crud.domain.ports.UserRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteUserUseCase {

  private final UserRepository userRepository;

  public void deleteUser(UUID uuid) {
    userRepository.delete(uuid);
  }
}
