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
    private UserRepository userRepository;
    @Autowired
    private RoleServiceImpl roleService;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO createUser(UserCreateDTO userCreateDto) {
        User userFromDto = userMapper.toEntity(userCreateDto);
        userFromDto.setRoleId(roleService.getDefaultRole());
        User user = userRepository.save(userFromDto);
        return userMapper.toDTO(user);
    }

    @Override
    public User getUser(int id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Unexpected user id!"));
    }

    @Override
    public boolean isUserExist(int id) {
        return userRepository.existsById(id);
    }
}
