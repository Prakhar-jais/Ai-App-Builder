package com.client.project.app_builder.service.impl;

import com.client.project.app_builder.dto.auth.UserProfileResponse;
import com.client.project.app_builder.dto.subscription.PlanLimitsResponse;
import com.client.project.app_builder.dto.subscription.UsageTodayResponse;
import com.client.project.app_builder.error.ResourceNotFoundException;
import com.client.project.app_builder.repository.UserRepository;
import com.client.project.app_builder.service.UsageService;
import com.client.project.app_builder.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService, UserDetailsService {

    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(()-> new ResourceNotFoundException("user",username));
    }

    @Override
    public UserProfileResponse getProfile(Long userId) {
        return null;
    }
}
