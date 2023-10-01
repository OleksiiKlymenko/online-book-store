package com.example.bookstore.service.impl;

import com.example.bookstore.model.Role;
import com.example.bookstore.model.RoleName;
import com.example.bookstore.repository.RoleRepository;
import com.example.bookstore.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getRoleByRoleName(RoleName roleName) {
        return roleRepository.findRoleByRoleName(roleName).orElseThrow(() ->
                        new RuntimeException("Can't find role by roleName: " + roleName));
    }
}
