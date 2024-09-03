package com.vincennlin.mahjongtrackerbackend.service.user;

public interface AuthService {

    Long getCurrentUserId();

    void authorizeOwnership(Long ownerId);

    Boolean containsAuthority(String authorityName);

    String getAuthorization();
}
