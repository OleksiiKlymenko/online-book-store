package com.example.bookstore.service;

import com.example.bookstore.model.Role;
import com.example.bookstore.model.RoleName;

public interface RoleService {
    Role getRoleByRoleName(RoleName roleName);
}
