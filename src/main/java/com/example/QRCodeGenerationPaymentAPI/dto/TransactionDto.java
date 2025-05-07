package com.example.QRCodeGenerationPaymentAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    private String id;
    private Double amount;
    private String currency;
    private String merchantId;
    private String userId;
    private String description;
    private Double balance;

    public TransactionDto(String id, Double amount, String currency) {
        this.id = id;
        this.amount = amount;
        this.currency = currency;
    }

    public String getTransactionId() {
        return this.id;
    }
}
