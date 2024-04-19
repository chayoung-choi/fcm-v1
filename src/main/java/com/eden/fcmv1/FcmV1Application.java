package com.eden.fcmv1;

import com.eden.fcmv1.config.FCMInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FcmV1Application {

  public static void main(String[] args) {
    if (!FCMInitializer.initialize()) {
      return;
    }
    SpringApplication.run(FcmV1Application.class, args);
  }
}
