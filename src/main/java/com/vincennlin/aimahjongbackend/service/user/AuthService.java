package com.vincennlin.aimahjongbackend.service.user;

import com.vincennlin.aimahjongbackend.entity.user.User;

public interface AuthService {

    User getCurrentUser();

    Long getCurrentUserId();

    void authorizeOwnership(Long ownerId);

    Boolean containsAuthority(String authorityName);

    String getAuthorization();
}
