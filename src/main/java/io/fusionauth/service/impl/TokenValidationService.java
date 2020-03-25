package io.fusionauth.service.impl;

import io.fusionauth.client.FusionAuthClient;
import io.fusionauth.dto.ResponseDto;
import io.fusionauth.dto.TokenDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static io.fusionauth.util.Util.MESSAGE_THE_TOKEN_IS_NOT_VALID;
import static io.fusionauth.util.Util.MESSAGE_THE_TOKEN_IS_VALID;

@Slf4j
@Service
@AllArgsConstructor
public class TokenValidationService {

  private FusionAuthClient fusionAuthClient;

  public ResponseDto isTokenValid(TokenDto token) {
    log.info("Token validation.");
    boolean isTokenValid = fusionAuthClient.validateJWT(token.getToken()).wasSuccessful();
    if(isTokenValid){
      Map<String, Object> data = new HashMap<>();
      data.put("token", token.getToken());
      return ResponseDto.builder()
              .success(Boolean.TRUE)
              .message(MESSAGE_THE_TOKEN_IS_VALID)
              .data(data)
              .build();
    }
    return ResponseDto.builder()
            .success(Boolean.FALSE)
            .message(MESSAGE_THE_TOKEN_IS_NOT_VALID)
            .data(null)
            .build();
  }
}
