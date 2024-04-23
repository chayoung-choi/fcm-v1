package com.eden.fcmv1.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.google.firebase.messaging.TopicManagementResponse;
import jakarta.annotation.PostConstruct;
import java.io.InputStream;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class FcmService {

  public static final String DEFAULT_FCM_TOPIC = "default";
  @Value("${firebase.service-account-file}")
  private String serviceAccountFilePath;

  @PostConstruct
  public void initialize() {
    ClassPathResource resource = new ClassPathResource(serviceAccountFilePath);
    try (InputStream stream = resource.getInputStream()) {
      FirebaseOptions options = FirebaseOptions.builder()
          .setCredentials(GoogleCredentials.fromStream(stream))
          .build();

      if (FirebaseApp.getApps().isEmpty()) {
        FirebaseApp.initializeApp(options);
        log.info("FirebaseApp initialization complete");
      } else {
        log.info("FirebaseApp has been already initialized");
      }
    } catch (Exception e) {
      log.error("Firebase Initialize Error!!\n=>{}", e.getMessage());
    }
  }

  public void sendMessageByToken(String title, String body, String token)
      throws FirebaseMessagingException {
    FirebaseMessaging.getInstance().send(Message.builder()
        .setNotification(Notification.builder()
            .setTitle(title)
            .setBody(body)
            .build())
        .setToken(token)
        .build());
  }

  public void sendMessageByTopic(String title, String body, String topic)
      throws FirebaseMessagingException {
    FirebaseMessaging.getInstance().send(Message.builder()
        .setNotification(Notification.builder()
            .setTitle(title)
            .setBody(body)
            .build())
        .setTopic(topic)
        .build());
  }

  public void subscribeToTopic(String topic, String token) {
    try {
      TopicManagementResponse response = FirebaseMessaging.getInstance().subscribeToTopic(
          Collections.singletonList(token), topic);
      log.info("FCM 구독 성공 : {}", response.getSuccessCount());
    } catch (FirebaseMessagingException e) {
      log.error("FCM 구독 실패 : {}", e.getMessage());
    }
  }

  public void unsubscribeFromTopic(String topic, String token) {
    try {
      TopicManagementResponse response = FirebaseMessaging.getInstance().unsubscribeFromTopic(
          Collections.singletonList(token), topic);
      log.info("FCM 구독 해제 성공 : {}", response.getSuccessCount());
    } catch (FirebaseMessagingException e) {
      log.error("FCM 구독 해제 실패 : {}", e.getMessage());
    }
  }
}
