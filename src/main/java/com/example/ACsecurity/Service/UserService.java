package com.example.ACsecurity.Service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.ACsecurity.DTO.UserRegistrationDto;
import com.example.ACsecurity.model.User;

public interface UserService extends UserDetailsService {
   User findByEmail(String email);
   User save(UserRegistrationDto registration);
}
