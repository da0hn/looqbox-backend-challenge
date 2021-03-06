package br.com.gabriel.looqbox.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableCaching
@SpringBootApplication
public class LooqboxBackendChallengeApplication {

  public static void main(final String[] args) {
    SpringApplication.run(LooqboxBackendChallengeApplication.class, args);
  }

}
