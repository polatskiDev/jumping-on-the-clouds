package io.techlab.data.response;

import java.io.Serializable;
import lombok.Data;

@Data
public class JotcProcessResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;
  private String fullName;
  private String email;
  private Long totalProcess;
}
