package com.teachmeskills.bartender_assistant.service.impl;

import com.teachmeskills.bartender_assistant.dto.UserCreateDTO;
import com.teachmeskills.bartender_assistant.dto.UserDTO;
import com.teachmeskills.bartender_assistant.entity.User;

public interface UserService {

    UserDTO createUser(UserCreateDTO userCreateDTO);

    User getUser(int id);

    boolean isUserExist(int id);
}
