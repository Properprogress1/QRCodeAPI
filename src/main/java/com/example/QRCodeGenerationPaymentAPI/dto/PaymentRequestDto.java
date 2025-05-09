package com.example.QRCodeGenerationPaymentAPI.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PaymentRequestDto {
    @NotNull
    @Positive
    private Double amount;

    @NotNull
    private String currency;

    @NotNull
    private String merchantId;

    private String description;

    private String userId;




}

