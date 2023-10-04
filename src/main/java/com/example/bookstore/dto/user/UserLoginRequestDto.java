package com.example.bookstore.dto.user;

import com.example.bookstore.lib.PasswordValidator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserLoginRequestDto {
    @Size(min = 7, max = 25)
    @Email
    private String email;

    @PasswordValidator
    private String password;

    public UserLoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
