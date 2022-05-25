package br.com.gabriel.looqbox.challenge.application.controllers;

import br.com.gabriel.looqbox.challenge.application.response.HealthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {

  @GetMapping
  public ResponseEntity<HealthResponse> health() {
    return ResponseEntity.ok(new HealthResponse("UP", "pokemon-service"));
  }

}
