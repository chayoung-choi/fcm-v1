package com.eden.fcmv1.controller;

import com.eden.fcmv1.dto.RegisterUserFcmTokenDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/fcm")
public class FcmApiController {

  @PostMapping("/v1/token/register")
  public void registerFcmToken(RegisterUserFcmTokenDto registerUserFcmTokenDto) {
    log.info("Registering FCM token: {}", registerUserFcmTokenDto);
  }
}
