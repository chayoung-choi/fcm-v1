package com.eden.fcmv1.dto;

import lombok.Data;

/**
 * FCM Message
 */
@Data
public class FcmMessageDto {

  private Long id;
  private String title;
  private String message;
}
