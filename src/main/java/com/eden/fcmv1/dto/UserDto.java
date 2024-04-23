package com.eden.fcmv1.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 회원
 */
@ToString
@Setter
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

  private Long id;
  private String name;
  private String fcmToken;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
