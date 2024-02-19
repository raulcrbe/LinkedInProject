package com.linkedin.sports.user.crud.application.search_users;

import com.linkedin.sports.user.crud.domain.models.User;
import com.linkedin.sports.user.crud.domain.ports.UserRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchUserUseCase {

  private final UserRepository userRepository;

  public User findUser(UUID id) {
    return userRepository.findById(id);
  }
}
