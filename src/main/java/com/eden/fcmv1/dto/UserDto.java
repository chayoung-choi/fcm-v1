package com.eden.fcmv1.dto;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * 회원
 */
@Data
public class UserDto {

  private Long id;
  private String name;
  private String fcmToken;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
