package com.eden.fcmv1.controller;

import com.eden.fcmv1.dto.UserDto;
import com.eden.fcmv1.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserApiController {

  private final UserService userService;

  @GetMapping("/api/v1/users")
  public ResponseEntity<List<UserDto>> getUsers() {
    log.info("UserApiController.getUsers");
    List<UserDto> userList = userService.getUserList();
    return ResponseEntity.status(HttpStatus.OK).body(userList);
  }

  @PostMapping("/api/v1/users/join")
  public ResponseEntity<Long> joinUser(@RequestBody UserDto user) {
    log.info("Joining user {}", user);
    Long id = userService.join(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(id);
  }

  @PostMapping("/api/v1/user/{id}")
  public ResponseEntity<Long> saveUser(@PathVariable(name = "id") String id,
      @RequestBody UserDto user) {
    log.info("Save user {}::{}", id, user);
    if (!id.equals(String.valueOf(user.getId()))) {
      throw new IllegalArgumentException("Invalid ID");
    }
    Long uid = userService.save(user);
    return ResponseEntity.status(HttpStatus.OK).body(uid);
  }
}
