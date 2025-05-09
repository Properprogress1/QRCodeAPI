package com.example.QRCodeGenerationPaymentAPI.controller;

import com.example.QRCodeGenerationPaymentAPI.apiResponse.ApiResponse;
import com.example.QRCodeGenerationPaymentAPI.dto.OtpRequestDto;
import com.example.QRCodeGenerationPaymentAPI.dto.TwoFactorRequestDto;
import com.example.QRCodeGenerationPaymentAPI.service.OtpService;
import com.example.QRCodeGenerationPaymentAPI.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class OtpController {

    private final UserService userService;
    private final OtpService otpService;

    @PostMapping("/{userId}/2fa/enable")
    public ResponseEntity<ApiResponse<String>> enableTwoFactor(@PathVariable String userId, @RequestBody TwoFactorRequestDto request) {
        userService.enableTwoFactor(userId, request.getPhoneNum());
        return ResponseEntity.ok(new ApiResponse<>("success", "2FA enabled", null));
    }

    @PostMapping("/otp/generate")
    public ResponseEntity<ApiResponse<String>> generateOtp(@RequestBody OtpRequestDto request) {
//        String otp = otpService.generateOtp(request.getUserId, request.getPhoneNum());
        String otp = String.valueOf(otpService.generateOtp(request.getUserId(), request.getPhoneNum()));

        return ResponseEntity.ok(new ApiResponse<>("success", "OTP generated", null));
    }
}
