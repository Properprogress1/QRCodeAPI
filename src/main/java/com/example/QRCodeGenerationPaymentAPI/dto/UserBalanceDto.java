package com.example.QRCodeGenerationPaymentAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBalanceDto {
    private String userId;
    private Double amount;
    private Double balance;

    // Add this custom constructor
    public UserBalanceDto(String userId) {
        this.userId = userId;
    }
}
