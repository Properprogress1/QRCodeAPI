package com.example.QRCodeGenerationPaymentAPI.serviceImpl;

import com.example.QRCodeGenerationPaymentAPI.dto.UserDto;
import com.example.QRCodeGenerationPaymentAPI.model.User;
import com.example.QRCodeGenerationPaymentAPI.repository.UserRepository;
import com.example.QRCodeGenerationPaymentAPI.service.NotificationService;
import com.example.QRCodeGenerationPaymentAPI.service.OtpService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {

    private final SecureRandom secureRandom = new SecureRandom();
    private final NotificationService notificationService;
    private final UserRepository userRepository;

    private final Map<String, Instant> lastRequestTimestamps = new ConcurrentHashMap<>();

    @Value("${otp.expiration.minutes:5}")
    private int expirationMinutes;

    @Value("${otp.rateLimit.seconds:60}")
    private int rateLimitInSeconds;

    @PostConstruct
    public void init() {
        System.out.println("OTP expiry: " + expirationMinutes + " min, rate-limit: " + rateLimitInSeconds + " sec");
    }

    @Override
    public UserDto generateOtp(String userId, String phoneNum) {
        Instant now = Instant.now();
        Instant lastRequest = lastRequestTimestamps.get(userId);

        if (lastRequest != null && now.isBefore(lastRequest.plusSeconds(rateLimitInSeconds))) {
            throw new RuntimeException("Too many OTP requests. Please wait before requesting again.");
        }

        String otp = String.format("%06d", secureRandom.nextInt(1_000_000));
        lastRequestTimestamps.put(userId, now);

        // Update user with phone number if needed
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = optionalUser.get();
        user.setPhoneNum(phoneNum); // ensure your User entity has this field
        user.setOtp(otp);
        user.setOtpExpiry(Instant.now().plusSeconds(expirationMinutes * 60));
        userRepository.save(user);

        // Send OTP via SMS/email
        notificationService.sendOtp(userId, otp);

        return new UserDto(user.getId(), user.getEmail(), user.getPhoneNum(), true);
    }

    @Override
    public boolean verifyOtp(String userId, String otp) {
        return false;
    }
}
