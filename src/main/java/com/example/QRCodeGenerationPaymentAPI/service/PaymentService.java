package com.example.QRCodeGenerationPaymentAPI.service;

import com.example.QRCodeGenerationPaymentAPI.dto.PaymentRequestDto;
import com.example.QRCodeGenerationPaymentAPI.dto.PaymentResponseDto;
import com.example.QRCodeGenerationPaymentAPI.dto.QRCodeResponseDto;
import com.example.QRCodeGenerationPaymentAPI.model.ScheduledPayment;
import com.example.QRCodeGenerationPaymentAPI.model.Transaction;
import jakarta.validation.Valid;

import java.util.List;


public interface PaymentService {
    QRCodeResponseDto generateQRCode(@Valid PaymentRequestDto request);
    PaymentResponseDto processPayment(String transactionId);
    PaymentResponseDto getPaymentStatus(String transactionId);
    void validatePaymentRequest(PaymentRequestDto request);

    QRCodeResponseDto getTransactions(QRCodeResponseDto request);

    PaymentResponseDto processPayment(String transactionId, String otp);


    PaymentRequestDto mapToPaymentRequest(ScheduledPayment payment);

}