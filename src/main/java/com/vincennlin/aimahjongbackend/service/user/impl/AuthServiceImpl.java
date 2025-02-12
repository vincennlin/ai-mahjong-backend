package com.vincennlin.aimahjongbackend.service.user.impl;

import com.vincennlin.aimahjongbackend.entity.user.User;
import com.vincennlin.aimahjongbackend.exception.ResourceOwnershipException;
import com.vincennlin.aimahjongbackend.service.user.AuthService;
import com.vincennlin.aimahjongbackend.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    @Override
    public User getCurrentUser() {
        return userService.getUserEntityByUserId(getCurrentUserId());
    }

    @Override
    public Long getCurrentUserId() {
        return Long.parseLong(getAuthentication().getPrincipal().toString());
    }

    @Override
    public void authorizeOwnership(Long ownerId) {
        Long currentUserId = getCurrentUserId();
        if (!currentUserId.equals(ownerId) && !containsAuthority("ADVANCED")) {
            throw new ResourceOwnershipException(currentUserId);
        }
    }

    @Override
    public Boolean containsAuthority(String authorityName) {
        return getAuthentication().getAuthorities().stream().anyMatch(
                authority -> authority.getAuthority().equals(authorityName));
    }

    @Override
    public String getAuthorization() {
        return getAuthentication().getCredentials().toString();
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
