package com.example.QRCodeGenerationPaymentAPI.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
    public class PaymentEventDto {
        private String transactionId;
        private String merchantId;
        private Double amount;
        private String status;
        private String errorMessage;

}
