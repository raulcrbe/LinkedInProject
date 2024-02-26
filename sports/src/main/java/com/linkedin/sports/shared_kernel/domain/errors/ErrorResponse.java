package com.linkedin.sports.shared_kernel.domain.errors;

import org.springframework.http.HttpStatus;

public interface ErrorResponse {
  String getKey();

  String getMessage();

  HttpStatus getHttpStatus();
}
