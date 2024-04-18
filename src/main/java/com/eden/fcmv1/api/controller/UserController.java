package com.eden.fcmv1.api.controller;

import com.eden.fcmv1.api.dto.UserDto;
import com.eden.fcmv1.api.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @PostMapping("/join")
  public ResponseEntity<Long> joinUser(@RequestBody UserDto user) {
    log.info("Joining user {}", user);
    Long id = userService.join(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(id);
  }
}
