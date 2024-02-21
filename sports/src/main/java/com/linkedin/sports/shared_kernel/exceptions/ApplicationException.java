package com.linkedin.sports.shared_kernel.exceptions;

import com.linkedin.sports.shared_kernel.errors.ErrorResponse;
import java.io.Serial;
import java.util.Locale;
import java.util.Map;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

@Slf4j
public class ApplicationException extends RuntimeException {
  @Serial private static final long serialVersionUID = 1L;

  @Getter private final transient ErrorResponse errorResponse;
  private final transient Map<String, Object> messageArguments;

  public ApplicationException(ErrorResponse errorResponse, Map<String, Object> messageArguments) {
    this.errorResponse = errorResponse;
    this.messageArguments = messageArguments;
  }

  public ApplicationException(
      ErrorResponse errorResponse, Map<String, Object> messageArguments, Throwable cause) {
    super(cause);
    this.errorResponse = errorResponse;
    this.messageArguments = messageArguments;
  }

  public ApplicationException(ErrorResponse errorResponse) {
    this.errorResponse = errorResponse;
    this.messageArguments = Map.of();
  }

  public ApplicationException(ErrorResponse errorResponse, Throwable cause) {
    super(cause);
    this.errorResponse = errorResponse;
    this.messageArguments = Map.of();
  }

  @Override
  public String getMessage() {
    return messageArguments.isEmpty()
        ? errorResponse.getMessage()
        : StringSubstitutor.replace(errorResponse.getMessage(), messageArguments, "{", "}");
  }

  public String getLocalizedMessage(Locale locale, MessageSource messageSource) {
    try {
      String localizedMessage =
          messageSource.getMessage(errorResponse.getKey(), new Object[] {}, locale);
      return messageArguments.isEmpty()
          ? localizedMessage
          : StringSubstitutor.replace(localizedMessage, messageArguments, "{", "}");
    } catch (NoSuchMessageException exception) {
      log.warn(
          "Please consider adding localized message for key {} and locale {}",
          errorResponse.getKey(),
          locale);
    }
    return getMessage();
  }
}
