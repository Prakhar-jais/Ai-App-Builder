package com.client.project.app_builder.service;

import com.client.project.app_builder.dto.auth.AuthResponse;
import com.client.project.app_builder.dto.auth.LoginRequest;
import com.client.project.app_builder.dto.auth.SignupRequest;

public interface AuthService {
     AuthResponse signup(SignupRequest request);

     AuthResponse login(LoginRequest request);
}
