package com.example.QRCodeGenerationPaymentAPI.controller;

import com.example.QRCodeGenerationPaymentAPI.apiResponse.ApiResponse;
import com.example.QRCodeGenerationPaymentAPI.dto.PaymentRequestDto;
import com.example.QRCodeGenerationPaymentAPI.dto.PaymentResponseDto;
import com.example.QRCodeGenerationPaymentAPI.dto.QRCodeResponseDto;
import com.example.QRCodeGenerationPaymentAPI.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/generate-qr")
    public ResponseEntity<ApiResponse<QRCodeResponseDto>> generateQRCode(
            @RequestBody PaymentRequestDto request) {
        QRCodeResponseDto response = paymentService.generateQRCode(request);
        return ResponseEntity.ok(new ApiResponse<>("success", "QR Code generated successfully", response));
    }

    @PostMapping("/process/{transactionId}")
    public ResponseEntity<ApiResponse<PaymentResponseDto>> processPayment(
            @PathVariable String transactionId) {
        PaymentResponseDto response = paymentService.processPayment(transactionId);
        return ResponseEntity.ok(new ApiResponse<>("success", "Payment processed successfully", response));
    }

    @GetMapping("/status/{transactionId}")
    public ResponseEntity<ApiResponse<PaymentResponseDto>> getPaymentStatus(
            @PathVariable String transactionId) {
        PaymentResponseDto response = paymentService.getPaymentStatus(transactionId);
        return ResponseEntity.ok(new ApiResponse<>("success", "Payment status retrieved", response));
    }


}


