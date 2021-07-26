package org.jala.foundation.dev32.jwt.example.requests;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUser {

  @NotBlank
  private String userName;
  @NotBlank
  private String password;
}
