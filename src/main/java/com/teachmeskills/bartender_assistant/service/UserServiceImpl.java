package com.teachmeskills.bartender_assistant.service;

import com.teachmeskills.bartender_assistant.dto.UserCreateDTO;
import com.teachmeskills.bartender_assistant.dto.UserDTO;
import com.teachmeskills.bartender_assistant.entity.User;
import com.teachmeskills.bartender_assistant.mapper.UserMapper;
import com.teachmeskills.bartender_assistant.repository.UserRepository;
import com.teachmeskills.bartender_assistant.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

//    private final UserMapper userMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDTO createUser(UserCreateDTO userCreateDto) {
        User userFromDto = userMapper.toEntity(userCreateDto);
        userFromDto.setRoleId(2);
        User user = userRepository.save(userFromDto);
        return userMapper.toDTO(user);
    }
}
