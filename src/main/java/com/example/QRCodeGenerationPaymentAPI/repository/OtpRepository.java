package com.example.QRCodeGenerationPaymentAPI.repository;

import com.example.QRCodeGenerationPaymentAPI.model.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Locale;

public interface OtpRepository extends JpaRepository<Otp, String> {


    static Locale findByUserIdAndCode(String userId, String otp) {
        return null;
    }
}
