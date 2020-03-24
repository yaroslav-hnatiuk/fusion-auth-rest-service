package io.fusionauth.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class TestController {

  @GetMapping(path = "/public/hello", produces = "application/json")
  public ResponseEntity<String> publicHello() {
    log.info("Hello from public end point.");
    return ResponseEntity.ok("Hello to from public end point.");
  }

  @GetMapping(path = "/protected/hello")
  public ResponseEntity<String> protectedHello() {
    log.info("Hello from protected end point.");
    return ResponseEntity.ok("Hello from protected end point.");
  }
}
