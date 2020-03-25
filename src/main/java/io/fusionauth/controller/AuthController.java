package io.fusionauth.controller;

import io.fusionauth.dto.CredentialsDto;
import io.fusionauth.dto.ResponseDto;
import io.fusionauth.dto.TokenDto;
import io.fusionauth.service.IUserService;
import io.fusionauth.service.impl.TokenValidationService;
import io.fusionauth.util.Util;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class AuthController {

  private IUserService userService;

  private TokenValidationService tokenValidation;

  @PostMapping(path = "/public/signin",
      produces = "application/json",
      consumes = "application/json"
  )
  public ResponseEntity<ResponseDto> login(@RequestBody CredentialsDto credentialsDto) {
    ResponseDto response = userService.login(credentialsDto);
    if (response.isSuccess()) {
      log.info("Successfully logged in.");
      return ResponseEntity.ok(response);
    }
    log.info("Successfully was not logged in.");
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @PostMapping(path = "/public/validate-token",
      produces = "application/json",
      consumes = "application/json"
  )
  public ResponseEntity<ResponseDto> isTokenValid(@RequestBody TokenDto token) {
    ResponseDto response = tokenValidation.isTokenValid(token);
    if (response.isSuccess()){
      log.info("The token is valid.");
      return ResponseEntity.ok(response);
    }
    log.info("The token is not valid.");
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }
}
