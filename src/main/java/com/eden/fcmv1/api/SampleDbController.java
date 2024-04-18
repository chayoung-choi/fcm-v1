package com.eden.fcmv1.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/sample")
@RestController
public class SampleDbController {

  @GetMapping("/save-db")
  public void saveData() {

  }
}
