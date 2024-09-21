package com.teachmeskills.bartender_assistant.service.impl;

import java.util.List;

import com.teachmeskills.bartender_assistant.entity.Role;

public interface RoleService {

    Role getDefaultRole();

    List<Role> getAllRoles();
}
