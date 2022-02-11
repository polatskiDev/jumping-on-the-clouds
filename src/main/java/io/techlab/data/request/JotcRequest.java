package io.techlab.data.request;

import java.util.List;
import lombok.Data;

@Data
public class JotcRequest {

  private String fullName;
  private String email;
  private List<Integer> jotcArray;


}
