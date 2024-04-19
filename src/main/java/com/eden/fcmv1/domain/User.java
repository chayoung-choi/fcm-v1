package com.eden.fcmv1.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "USERS")
public class User extends BaseTime {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  private String fcmToken;

  public void update(String name) {
    this.name = name;
  }
}
