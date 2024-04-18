package com.eden.fcmv1.api.service;

import com.eden.fcmv1.api.domain.User;
import com.eden.fcmv1.api.dto.UserDto;
import com.eden.fcmv1.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;

  public Long join(UserDto userDto) {
    User user = User.builder().name(userDto.getName()).build();
    return userRepository.save(user).getId();
  }
}
