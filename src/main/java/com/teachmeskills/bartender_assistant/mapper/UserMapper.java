package com.teachmeskills.bartender_assistant.mapper;

import java.util.List;

import com.teachmeskills.bartender_assistant.dto.UserCreateDTO;
import com.teachmeskills.bartender_assistant.dto.UserDTO;
import com.teachmeskills.bartender_assistant.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);

    User toEntity(UserCreateDTO userCreateDTO);

    List<UserDTO> toDTOList(List<User> usersList);
}
