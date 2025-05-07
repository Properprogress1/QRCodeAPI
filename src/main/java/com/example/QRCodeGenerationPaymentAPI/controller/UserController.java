package com.example.QRCodeGenerationPaymentAPI.controller;

import com.example.QRCodeGenerationPaymentAPI.apiResponse.ApiResponse;
import com.example.QRCodeGenerationPaymentAPI.dto.UserBalanceDto;
import com.example.QRCodeGenerationPaymentAPI.dto.UserDto;
import com.example.QRCodeGenerationPaymentAPI.dto.UserRegistrationDto;
import com.example.QRCodeGenerationPaymentAPI.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



    @RestController
    @RequestMapping("/api/v1/users")
    @RequiredArgsConstructor
    public class UserController {

        private final UserService userService;

        @PostMapping("/register")
        public ResponseEntity<ApiResponse<UserDto>> registerUser(@Valid @RequestBody UserRegistrationDto request) {
            UserDto user = userService.registerUser(request);
            return ResponseEntity.ok(new ApiResponse<>("success", "User registered successfully", user));
        }

        @GetMapping("/{userId}/balance")
        public ResponseEntity<ApiResponse<UserBalanceDto>> getUserBalance(@PathVariable String userId) {
            UserBalanceDto request = new UserBalanceDto(userId);
            UserBalanceDto userBalance = userService.getUserBalance(request);
            return ResponseEntity.ok(new ApiResponse<>("success", "User balance retrieved", userBalance));
        }


        @PostMapping("/{userId}/credit")
        public ResponseEntity<ApiResponse<UserBalanceDto>> creditUserAccount(
                @PathVariable String userId,
                @RequestBody UserBalanceDto requestBody) {
            UserBalanceDto updatedBalance = userService.creditUserAccount(requestBody);

            return ResponseEntity.ok(new ApiResponse<>("success", "User account credited", updatedBalance));
        }



        @GetMapping("/{userId}")
        public ResponseEntity<ApiResponse<UserDto>> getUserProfile(@PathVariable String userId) {
            UserDto user = userService.getUserProfile(userId);
            return ResponseEntity.ok(new ApiResponse<>("success", "User profile retrieved", user));
        }

        @PutMapping("/{userId}")
        public ResponseEntity<ApiResponse<UserDto>> updateUserProfile(
                @PathVariable String userId,
                @RequestBody UserDto updatedUser) {

            updatedUser.setId(userId);
            UserDto user = userService.updateUserProfile(updatedUser);
            return ResponseEntity.ok(new ApiResponse<>("success", "User profile updated", user));
        }

    }

