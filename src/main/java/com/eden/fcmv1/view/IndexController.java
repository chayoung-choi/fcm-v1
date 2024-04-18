package com.eden.fcmv1.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

  @GetMapping(value = "/")
  public String index() {
    System.out.println("!11");

    return "index";
  }
}
