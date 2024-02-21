package com.linkedin.sports.user.crud.infrastructure.controllers;

import com.linkedin.sports.user.crud.application.create_user.CreateUserUseCase;
import com.linkedin.sports.user.crud.application.delete_user.DeleteUserUseCase;
import com.linkedin.sports.user.crud.application.search_users.SearchUserUseCase;
import com.linkedin.sports.user.crud.application.update_user.UpdateUserUseCase;
import com.linkedin.sports.user.crud.domain.models.User;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final SearchUserUseCase searchUserUseCase;
  private final DeleteUserUseCase deleteUserUseCase;
  private final UpdateUserUseCase updateUserUseCase;
  private final CreateUserUseCase createUserUseCase;

  @GetMapping("/{id}")
  public ResponseEntity<User> searchUser(@PathVariable("id") UUID id) {
    return ResponseEntity.ok(searchUserUseCase.findUser(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<User> deleteUser(@PathVariable("id") UUID id) {
    deleteUserUseCase.deleteUser(id);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<User> updateUser(@PathVariable("id") UUID id, @RequestBody User user) {
    user.setId(id);
    updateUserUseCase.updateUser(user);
    return ResponseEntity.ok().build();
  }

  @PostMapping
  public ResponseEntity<Void> createUser(@RequestBody User user) {
    createUserUseCase.createUser(user);
    return ResponseEntity.ok().build();
  }
}
