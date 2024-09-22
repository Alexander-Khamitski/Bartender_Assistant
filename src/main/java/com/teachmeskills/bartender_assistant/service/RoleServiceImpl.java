package com.teachmeskills.bartender_assistant.service;

import java.util.List;

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
        return roleRepository.findById(RoleIdsConsts.ROLE_USER)
                             .orElseThrow(() -> new IllegalArgumentException("Unexpected role id!"));
    }

    @Override
    public Role getRoleById(int id) {
        return roleRepository.findById(id)
                             .orElseThrow(() -> new IllegalArgumentException("Unexpected role id: " + id));
    }


    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
