package com.eden.fcmv1.controller;

import com.eden.fcmv1.dto.UserDto;
import com.eden.fcmv1.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Slf4j
@Controller
@RequiredArgsConstructor
public class ViewController {

  private final UserService userService;

  @GetMapping(value = "/")
  public String index() {
    log.info("ViewController.index");
    return "index";
  }


  @GetMapping(value = "/users")
  public String users(Model model) {
    log.info("ViewController.users");
    List<UserDto> userList = userService.getUserList();
    model.addAttribute("userList", userList);
    return "page/users";
  }

  @GetMapping(value = "/user/{id}")
  public String user(@PathVariable Long id, Model model) {
    log.info("ViewController.user :: {}", id);
    UserDto userDto = userService.getUser(id);
    model.addAttribute("user", userDto);
    return "page/user";
  }

  @GetMapping(value = "/user/join")
  public String userJoin() {
    log.info("ViewController.userJoin");
    return "page/user-join";
  }
}
