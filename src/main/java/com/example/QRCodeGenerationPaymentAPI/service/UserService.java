package com.example.QRCodeGenerationPaymentAPI.service;

import com.example.QRCodeGenerationPaymentAPI.dto.*;
import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
   UserDto registerUser (@Valid UserRegistrationDto request);

   UserBalanceDto getUserBalance (UserBalanceDto request);

   UserBalanceDto creditUserAccount(UserBalanceDto request);

   UserDto getUserProfile(String userId);
   UserDto updateUserProfile(UserDto updatedUser);

   UserDetails loadUserByUsername(String email);

   UserDto enableTwoFactor(String userId, String PhoneNumber);

}
