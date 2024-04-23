package com.eden.fcmv1.controller;

import com.eden.fcmv1.dto.FcmMessageDto;
import com.eden.fcmv1.dto.RegisterUserFcmTokenDto;
import com.eden.fcmv1.dto.UserDto;
import com.eden.fcmv1.service.FcmService;
import com.eden.fcmv1.service.UserService;
import com.google.firebase.messaging.FirebaseMessagingException;
import javax.naming.ServiceUnavailableException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/fcm")
@RequiredArgsConstructor
public class FcmApiController {

  private final FcmService fcmService;
  private final UserService userService;

  @PostMapping("/v1/token/register")
  public void registerFcmToken(RegisterUserFcmTokenDto registerUserFcmTokenDto) {
    log.info("Registering FCM token: {}", registerUserFcmTokenDto);
  }

  @PostMapping("/v1/send-message")
  public void sendFcmMessage(@RequestBody FcmMessageDto fcmMessageDto)
      throws ServiceUnavailableException {
    log.info("Sending FCM message: {}", fcmMessageDto);
    UserDto user = userService.getUser(fcmMessageDto.getId());
    try {
      fcmService.sendMessageByToken(fcmMessageDto.getTitle(), fcmMessageDto.getMessage(),
          user.getFcmToken());
    } catch (FirebaseMessagingException e) {
      log.error(e.getMessage());
      throw new ServiceUnavailableException("사용 불가.");
    }
  }
}
