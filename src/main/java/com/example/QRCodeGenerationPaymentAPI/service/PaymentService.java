package com.example.QRCodeGenerationPaymentAPI.service;

import com.example.QRCodeGenerationPaymentAPI.dto.PaymentRequestDto;
import com.example.QRCodeGenerationPaymentAPI.dto.PaymentResponseDto;
import com.example.QRCodeGenerationPaymentAPI.dto.QRCodeResponseDto;
import jakarta.validation.Valid;


public interface PaymentService {
    QRCodeResponseDto generateQRCode(@Valid PaymentRequestDto request);
    PaymentResponseDto processPayment(String transactionId);
    PaymentResponseDto getPaymentStatus(String transactionId);
    void validatePaymentRequest(PaymentRequestDto request);
}