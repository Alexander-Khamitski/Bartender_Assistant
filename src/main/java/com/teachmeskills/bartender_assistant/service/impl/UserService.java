package com.teachmeskills.bartender_assistant.service.impl;

import com.teachmeskills.bartender_assistant.dto.UserCreateDTO;
import com.teachmeskills.bartender_assistant.dto.UserDTO;

public interface UserService {

    UserDTO createUser(UserCreateDTO userCreateDTO);
}
