package io.fusionauth.service.impl;

import io.fusionauth.client.FusionAuthClient;
import io.fusionauth.dto.TokenDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class TokenValidationService {

  private FusionAuthClient fusionAuthClient;

  public boolean isTokenValid(TokenDto token) {
    log.info("Token validation.");
    return fusionAuthClient.validateJWT(token.getToken()).wasSuccessful();
  }
}
