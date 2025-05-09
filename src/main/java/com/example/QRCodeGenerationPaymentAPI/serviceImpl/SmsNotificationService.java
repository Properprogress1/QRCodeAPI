package com.example.QRCodeGenerationPaymentAPI.serviceImpl;

import com.example.QRCodeGenerationPaymentAPI.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SmsNotificationService implements NotificationService {

    @Override
    public void sendOtp(String phoneNumber, String otp) {
        // Simulate sending OTP via SMS
        log.info("Sending OTP {} to phone number {}", otp, phoneNumber);
    }
}
