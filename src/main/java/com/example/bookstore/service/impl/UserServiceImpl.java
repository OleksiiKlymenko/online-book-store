package com.example.bookstore.service.impl;

import com.example.bookstore.dto.user.UserRegistrationRequestDto;
import com.example.bookstore.dto.user.UserResponseDto;
import com.example.bookstore.exception.RegistrationException;
import com.example.bookstore.mapper.UserMapper;
import com.example.bookstore.model.RoleName;
import com.example.bookstore.model.User;
import com.example.bookstore.repository.UserRepository;
import com.example.bookstore.service.RoleService;
import com.example.bookstore.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final RoleService roleService;

    private final String adminEmail = "admin@example.com";

    @Override
    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new EntityNotFoundException("Can't find user by username "
                        + authentication.getName()));
    }

    @Override
    public UserResponseDto register(UserRegistrationRequestDto request)
            throws RegistrationException {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RegistrationException("User with this email already exists");
        }
        User user = userMapper.mapToModel(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRoles(Set.of(roleService.getRoleByRoleName(RoleName.ROLE_USER)));
        if (request.getEmail().equals(adminEmail)) {
            user.setRoles(Set.of(roleService.getRoleByRoleName(RoleName.ROLE_ADMIN)));
        }
        User savedUser = userRepository.save(user);
        return userMapper.mapToDto(savedUser);
    }
}
