package com.example.bookstore.service;

import com.example.bookstore.dto.user.UserRegistrationRequestDto;
import com.example.bookstore.dto.user.UserResponseDto;
import com.example.bookstore.exception.RegistrationException;
import com.example.bookstore.model.User;

public interface UserService {
    User getUser();

    UserResponseDto register(UserRegistrationRequestDto request) throws RegistrationException;
}
