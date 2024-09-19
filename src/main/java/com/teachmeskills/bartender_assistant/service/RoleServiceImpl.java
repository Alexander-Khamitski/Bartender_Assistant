package com.teachmeskills.bartender_assistant.service;

import com.teachmeskills.bartender_assistant.consts.RoleIdsConsts;
import com.teachmeskills.bartender_assistant.entity.Role;
import com.teachmeskills.bartender_assistant.repository.RoleRepository;
import com.teachmeskills.bartender_assistant.service.impl.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role getDefaultRole() {
        return roleRepository.findById(RoleIdsConsts.ROLE_ADMIN)
                             .orElseThrow(() -> new IllegalArgumentException("Unexpected role id!"));
    }
}
