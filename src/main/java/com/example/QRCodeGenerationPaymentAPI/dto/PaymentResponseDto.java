package com.example.QRCodeGenerationPaymentAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDto {
    private String transactionId;
    private String status;
    private Double userBalance;
    private Double merchantBalance;
    private String userId;
}