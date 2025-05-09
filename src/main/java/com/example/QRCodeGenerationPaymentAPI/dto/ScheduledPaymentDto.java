package com.example.QRCodeGenerationPaymentAPI.dto;

import com.example.QRCodeGenerationPaymentAPI.enums.PaymentStatus;
import com.example.QRCodeGenerationPaymentAPI.enums.Recurrence;
import lombok.*;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ScheduledPaymentDto {
    private String id;
    private String userId;
    private String merchantId;
    private Double amount;
    private String currency;
    private LocalDateTime scheduledDate;
    private Recurrence recurrence; // e.g., "DAILY", "WEEKLY", "MONTHLY"
    private PaymentStatus status;

}
