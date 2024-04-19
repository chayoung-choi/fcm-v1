/*
 *
 * PROJ : 대교 차세대 드림스 구축 프로젝트 Copyright © 2022 DAEKYO. All Rights Reserved
 *
 */

package com.eden.fcmv1.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

/**
 * FCM 초기화
 */
@Slf4j
public class FCMInitializer {

  private FCMInitializer() {
    throw new IllegalStateException("FCMInitializer class");
  }

  public static boolean initialize() {
    ClassPathResource resource = new ClassPathResource("firebase-service-account.json");

    try (InputStream stream = resource.getInputStream()) {
      FirebaseOptions options = FirebaseOptions.builder()
          .setCredentials(GoogleCredentials.fromStream(stream)).build();

      if (FirebaseApp.getApps().isEmpty()) {
        FirebaseApp.initializeApp(options);
        log.debug("FirebaseApp initialization complete");
      } else {
        log.debug("FirebaseApp has been already initialized");
      }
    } catch (Exception e) {
      log.error("Firebase Initialize Error!!\n=>{}", e.getMessage());
      return false;
    }
    return true;
  }
}
