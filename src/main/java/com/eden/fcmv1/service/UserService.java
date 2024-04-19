package com.eden.fcmv1.service;

import com.eden.fcmv1.domain.User;
import com.eden.fcmv1.dto.UserDto;
import com.eden.fcmv1.repository.UserRepository;
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
  private final ModelMapper modelMapper;

  @Transactional
  public Long join(UserDto userDto) {
    User user = User.builder().name(userDto.getName()).build();
    return userRepository.save(user).getId();
  }

  @Transactional
  public Long save(UserDto userDto) {
    User user = userRepository.findById(userDto.getId()).orElseThrow();
    user.update(userDto.getName());
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
}
