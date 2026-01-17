package com.client.project.app_builder.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public record JwtUserPrincipal(Long userId, String username, List<GrantedAuthority> authorities) {
}
