package com.linkedin.sports.shared_kernel.exceptions;

import com.linkedin.sports.shared_kernel.errors.ConstraintViolationError;
import com.linkedin.sports.shared_kernel.errors.HttpResponseConstant;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends DefaultErrorAttributes {

  private final MessageSource messageSource;

  @ExceptionHandler(ApplicationException.class)
  public ResponseEntity<Map<String, Object>> handle(ApplicationException ex, WebRequest request) {
    log.info("Required request body is missing {}", ex.getMessage());
    return ofType(request, ex.getErrorResponse().getHttpStatus(), ex);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public final ResponseEntity<Map<String, Object>> handle(
      MethodArgumentNotValidException ex, WebRequest request) {
    List<ConstraintViolationError> validationErrors =
        ex.getBindingResult().getFieldErrors().stream()
            .map(error -> new ConstraintViolationError(error.getField(), error.getDefaultMessage()))
            .toList();

    return ofType(request, HttpStatus.BAD_REQUEST, ex.getMessage(), null, validationErrors);
  }

  protected ResponseEntity<Map<String, Object>> ofType(
      WebRequest request, HttpStatus status, ApplicationException ex) {
    Locale locale = LocaleContextHolder.getLocale();
    return ofType(
        request,
        status,
        ex.getLocalizedMessage(locale, messageSource),
        ex.getErrorResponse().getKey(),
        Collections.emptyList());
  }

  private ResponseEntity<Map<String, Object>> ofType(
      WebRequest request, HttpStatus status, String message, String key, List<?> validationErrors) {
    Map<String, Object> attributes = getErrorAttributes(request, ErrorAttributeOptions.defaults());
    attributes.put(HttpResponseConstant.STATUS, status.value());
    attributes.put(HttpResponseConstant.ERROR, status);
    attributes.put(HttpResponseConstant.MESSAGE, message);
    attributes.put(HttpResponseConstant.ERRORS, validationErrors);
    attributes.put(HttpResponseConstant.ERROR_KEY, key);
    attributes.put(
        HttpResponseConstant.PATH, ((ServletWebRequest) request).getRequest().getRequestURI());
    return new ResponseEntity<>(attributes, status);
  }
}
