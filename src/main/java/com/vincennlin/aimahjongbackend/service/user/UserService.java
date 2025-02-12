package com.vincennlin.aimahjongbackend.service.user;

import com.vincennlin.aimahjongbackend.entity.user.User;
import com.vincennlin.aimahjongbackend.payload.user.*;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    RegisterResponse register(RegisterDto registerDto);

    UserDto getUserDetailsByUsername(String username);

    User getUserEntityByUserId(Long userId);

    UserDto getUserByUserId(Long userId);

    Long getCurrentUserId();

    AccountInfoDto getCurrentAccountInfo();

//    byte[] getProfilePicture();

    List<AccountInfoDto> getAllUsers();

    UpdateAccountInfoResponse updateAccountInfo(AccountInfoDto accountInfoDto);

    UpdateAccountInfoResponse changePassword(ChangePasswordRequest request);

//    UpdateAccountInfoResponse updateProfilePicture(MultipartFile profilePicture);
}
