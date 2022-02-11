package io.techlab.message;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Message {

  private String code;
  private String message;
  private Severity severity;

  public Message(String code, String message, Severity severity) {
    this.code = code;
    this.message = message;
    this.severity = severity;
  }

  @Override
  public String toString() {
    return "{" + "code : '" + code + '\'' + ", field:'" + ", message:'" + message + '\'' + ", severity : " + severity + '}';
  }
}
