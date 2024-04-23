package com.eden.fcmv1.service;

import static com.eden.fcmv1.service.FcmService.DEFAULT_FCM_TOPIC;

import com.eden.fcmv1.domain.User;
import com.eden.fcmv1.dto.UserDto;
import com.eden.fcmv1.repository.UserRepository;
import io.micrometer.common.util.StringUtils;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {

  private final UserRepository userRepository;
  private final FcmService fcmService;
  private final ModelMapper modelMapper;

  @Transactional
  public Long join(UserDto userDto) {
    User user = User.builder().name(userDto.getName()).build();
    return userRepository.save(user).getId();
  }

  @Transactional
  public Long save(UserDto userDto) {
    User user = modelMapper.map(userDto, User.class);
    if (StringUtils.isNotBlank(user.getFcmToken())) {
      fcmService.subscribeToTopic(DEFAULT_FCM_TOPIC, user.getFcmToken());
    }
    return userRepository.save(user).getId();
  }

  public UserDto getUser(Long id) {
    Optional<User> user = userRepository.findById(id);
    if (user.isEmpty()) {
      throw new NoSuchElementException("존재하지 않는 회원입니다.");
    }
    return modelMapper.map(user, UserDto.class);
  }

  public List<UserDto> getUserList() {
    List<User> userList = userRepository.findAll();
    return modelMapper.map(userList, new TypeToken<List<UserDto>>() {
    }.getType());
  }

  @Transactional
  public void deleteUser(Long id) {
    User user = userRepository.findById(id).orElseThrow();
    if (StringUtils.isNotBlank(user.getFcmToken())) {
      fcmService.unsubscribeFromTopic(DEFAULT_FCM_TOPIC, user.getFcmToken());
    }
    userRepository.deleteById(id);
  }
}
