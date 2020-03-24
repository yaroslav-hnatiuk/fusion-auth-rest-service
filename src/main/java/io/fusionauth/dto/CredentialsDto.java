package io.fusionauth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CredentialsDto {

  private String password;

  private String email;
}
