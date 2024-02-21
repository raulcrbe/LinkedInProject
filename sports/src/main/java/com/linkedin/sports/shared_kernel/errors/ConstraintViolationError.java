package com.linkedin.sports.shared_kernel.errors;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConstraintViolationError {
  private String property;
  private String message;
}
