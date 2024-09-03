package com.vincennlin.mahjongtrackerbackend.service.user;

import com.vincennlin.mahjongtrackerbackend.entity.user.User;

public interface AuthService {

    User getCurrentUser();

    Long getCurrentUserId();

    void authorizeOwnership(Long ownerId);

    Boolean containsAuthority(String authorityName);

    String getAuthorization();
}
