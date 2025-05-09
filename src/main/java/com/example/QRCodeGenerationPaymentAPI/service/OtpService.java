package com.example.QRCodeGenerationPaymentAPI.service;

import com.example.QRCodeGenerationPaymentAPI.dto.UserDto;

public interface OtpService {
    UserDto generateOtp(String userId, String phoneNum);

    boolean verifyOtp(String userId, String otp);
}

