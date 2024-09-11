package com.teachmeskills.bartender_assistant.validator;

import com.teachmeskills.bartender_assistant.dto.UserCreateDTO;

public class UserDTOValidator {

    public static boolean isUserCreateDTOValid(UserCreateDTO userCreateDTO) {
        return !userCreateDTO.getUsername().isEmpty()
                && !userCreateDTO.getLogin().isEmpty()
                && !userCreateDTO.getPassword().isEmpty();
    }
}
