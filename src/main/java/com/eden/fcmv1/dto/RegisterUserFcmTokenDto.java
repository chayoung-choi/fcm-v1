package com.eden.fcmv1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserFcmTokenDto {

  private Long id;
  private String token;
}
