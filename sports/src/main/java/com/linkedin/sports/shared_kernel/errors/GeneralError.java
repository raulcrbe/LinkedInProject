package com.linkedin.sports.shared_kernel.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralError implements ErrorResponse {
  ;
  final String key;
  final HttpStatus httpStatus;
  final String message;
}
