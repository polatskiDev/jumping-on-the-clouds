package io.techlab.component;

import io.techlab.message.Message;
import io.techlab.message.Severity;
import java.util.Locale;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

/**
 * This class is created to manage return messages of the Rest Response Methods
 */
@AllArgsConstructor
@Component
public class MessageComponent {

  private final MessageSource messageSource;

  public Message getErrorMessage(String code) {
    return new Message(code, messageSource.getMessage(code, new String[] {}, Locale.getDefault()), Severity.error);
  }

  public Message getInfoMessage(String code) {
    return new Message(code, messageSource.getMessage(code, new String[] {}, Locale.getDefault()), Severity.info);
  }
}
