package com.teachmeskills.bartender_assistant.service;

import com.teachmeskills.bartender_assistant.dto.UserCreateDTO;
import com.teachmeskills.bartender_assistant.dto.UserDTO;
import com.teachmeskills.bartender_assistant.entity.Role;
import com.teachmeskills.bartender_assistant.entity.User;
import com.teachmeskills.bartender_assistant.mapper.UserMapper;
import com.teachmeskills.bartender_assistant.repository.UserRepository;
import com.teachmeskills.bartender_assistant.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleServiceImpl roleService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public UserDTO createUser(UserCreateDTO userCreateDto) {
        User user = userMapper.toEntity(userCreateDto);
        String rawPassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(rawPassword));
        Role role = roleService.getDefaultRole();
        user.setRole(role);
        userRepository.save(user);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getLogin(), rawPassword);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
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
