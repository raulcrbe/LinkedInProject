package com.linkedin.sports.user.crud.domain;

import com.linkedin.sports.shared_kernel.domain.errors.ErrorResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum UserError implements ErrorResponse {
  USER_NOT_FOUND("USER_NOT_FOUND", HttpStatus.NOT_FOUND, "User with id {id} not found");

  final String key;
  final HttpStatus httpStatus;
  final String message;

  UserError(String key, HttpStatus httpStatus, String message) {
    this.message = message;
    this.key = key;
    this.httpStatus = httpStatus;
  }
}
