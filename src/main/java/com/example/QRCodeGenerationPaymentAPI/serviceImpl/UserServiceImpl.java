package com.example.QRCodeGenerationPaymentAPI.serviceImpl;

import com.example.QRCodeGenerationPaymentAPI.dto.*;
import com.example.QRCodeGenerationPaymentAPI.model.User;
import com.example.QRCodeGenerationPaymentAPI.repository.UserRepository;
import com.example.QRCodeGenerationPaymentAPI.service.OtpService;
import com.example.QRCodeGenerationPaymentAPI.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final OtpService otpService;

    @Override
    public UserDto registerUser(UserRegistrationDto request) {
        // Check if user with same email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("User already exists with this email");
        }

        // Create and save user
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .balance(0.0)
                .build();

        User savedUser = userRepository.save(user);

        return new UserDto(savedUser.getId(), savedUser.getName(), savedUser.getEmail());
    }

    @Override
    public UserBalanceDto getUserBalance(UserBalanceDto request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return UserBalanceDto.builder()
                .userId(user.getId())
                .balance(user.getBalance())
                .build();
    }

    @Override
    public UserBalanceDto creditUserAccount(UserBalanceDto request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        double newBalance = user.getBalance() + request.getAmount();
        user.setBalance(newBalance);
        userRepository.save(user);

        return UserBalanceDto.builder()
                .userId(user.getId())
                .amount(request.getAmount())
                .balance(newBalance)
                .build();
    }


    @Override
    public UserDto enableTwoFactor(String userId, String phoneNumber) {
        UserDto userDto = otpService.generateOtp(userId, phoneNumber);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setTwoFactorEnabled(true);
        userRepository.save(user);

        return new UserDto(user.getId(), user.getEmail(), user.getPhoneNum(), true); // ✅ fixed method name
    }



    @Override
    public UserDto getUserProfile(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new UserDto(user.getId(),user.getName(),user.getEmail());
    }

    @Override
    public UserDto updateUserProfile(UserDto userDto) {
        User user = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());

        User updatedUser = userRepository.save(user);

        return new UserDto(updatedUser.getId(), updatedUser.getName(), updatedUser.getEmail());
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(), // Assuming you store hashed passwords
                new ArrayList<>() // roles/authorities
        );
    }

}
