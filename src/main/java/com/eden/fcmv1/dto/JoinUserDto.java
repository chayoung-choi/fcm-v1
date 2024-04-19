package com.eden.fcmv1.dto;

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
public class JoinUserDto {

  private String name;
}
