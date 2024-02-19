package com.linkedin.sports.user.crud.infrastructure.controllers;

import com.linkedin.sports.user.crud.application.search_users.SearchUserUseCase;
import com.linkedin.sports.user.crud.domain.models.User;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final SearchUserUseCase searchUserUseCase;

  @GetMapping("/{id}")
  public ResponseEntity<User> searchUser(@PathVariable("id") UUID id) {
    return ResponseEntity.ok(searchUserUseCase.findUser(id));
  }
}
