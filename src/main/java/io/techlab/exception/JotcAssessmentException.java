package io.techlab.exception;

import java.util.Locale;
import org.springframework.context.MessageSource;

public class JotcAssessmentException extends RuntimeException{

  private static final long serialVersionUID = 1;
  private final String errorCode;
  private final Object[] messageArguments;

  /**
   * Set exception errorCode and messageArguments Extends RuntimeException
   */
  public JotcAssessmentException(Exception e) {
    super(e.getMessage());
    this.errorCode = null;
    this.messageArguments = null;
  }

  /**
   * Set exception errorCode and messageArguments
   */
  public JotcAssessmentException(String errorCode, Object... messageArguments) {
    this.errorCode = errorCode;
    this.messageArguments = messageArguments;
  }

  /**
   * Get errorCode of exception
   *
   * @return String
   */
  public String getErrorCode() {
    return errorCode;
  }

  /**
   * Get message of exception
   *
   * @return String
   */
  public String getMessage(MessageSource messageSource, Locale locale) {
    if (errorCode == null) {
      return this.getMessage();
    }
    else {
      return messageSource.getMessage(errorCode, messageArguments, locale);
    }
  }

}
