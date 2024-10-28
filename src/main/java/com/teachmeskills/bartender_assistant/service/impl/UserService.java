package com.teachmeskills.bartender_assistant.service.impl;

import com.teachmeskills.bartender_assistant.dto.UserCreateDTO;
import com.teachmeskills.bartender_assistant.dto.UserDTO;
import com.teachmeskills.bartender_assistant.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

public interface UserService {

    UserDTO createUser(UserCreateDTO userCreateDTO);

    UserDTO getUserDto(int id);

    User getUser(int id);

    User updateUser(User user);

    void deleteUser(int id);

    boolean isUserExist(int id);

    Page<UserDTO> getAllUsers(Pageable pageable);

    User getProfileInfo(Model model);

    boolean isUserExistByUsername(String username);

    boolean isUserExistByLogin(String login);

    Page<UserDTO> getBartenders(Pageable pageable);
}
