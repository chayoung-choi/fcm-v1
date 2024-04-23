package com.eden.fcmv1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FcmV1Application {

  public static void main(String[] args) {
    SpringApplication.run(FcmV1Application.class, args);
  }
}
