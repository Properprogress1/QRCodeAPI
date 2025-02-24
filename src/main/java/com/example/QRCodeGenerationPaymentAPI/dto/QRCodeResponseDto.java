package com.example.QRCodeGenerationPaymentAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QRCodeResponseDto {
    private String qrCodeBase64;
    private String transactionId;
}
