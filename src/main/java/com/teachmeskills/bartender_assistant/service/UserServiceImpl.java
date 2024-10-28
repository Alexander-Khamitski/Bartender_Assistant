package com.teachmeskills.bartender_assistant.service;

import java.util.List;

import com.teachmeskills.bartender_assistant.consts.RoleIdsConsts;
import com.teachmeskills.bartender_assistant.dto.UserCreateDTO;
import com.teachmeskills.bartender_assistant.dto.UserDTO;
import com.teachmeskills.bartender_assistant.entity.Role;
import com.teachmeskills.bartender_assistant.entity.User;
import com.teachmeskills.bartender_assistant.mapper.UserMapper;
import com.teachmeskills.bartender_assistant.repository.UserRepository;
import com.teachmeskills.bartender_assistant.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

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
    public UserDTO getUserDto(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Unexpected user id!"));
        return userMapper.toDTO(user);
    }

    @Override
    public User getUser(int id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Unexpected user id!"));
    }

    @Override
    public User updateUser(User user) {
        String rawPassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(rawPassword));
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean isUserExist(int id) {
        return userRepository.existsById(id);
    }

    public Page<UserDTO> getAllUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(user -> userMapper.toDTO(user));
    }

    public User getProfileInfo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        return userRepository.findByLogin(login);
    }

    public List<User> getBartenders() {
        return userRepository.findByRoleId(RoleIdsConsts.ROLE_BARTENDER);
    }

    @Override
    public boolean isUserExistByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean isUserExistByLogin(String login) {
        return userRepository.existsByLogin(login);
    }

    @Override
    public Page<UserDTO> getBartenders(Pageable pageable) {
        Page<User> users = userRepository.findByRoleId(RoleIdsConsts.ROLE_BARTENDER, pageable);
        return users.map(user -> userMapper.toDTO(user));
    }
}
