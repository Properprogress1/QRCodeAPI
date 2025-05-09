package com.example.QRCodeGenerationPaymentAPI.model;

import com.example.QRCodeGenerationPaymentAPI.enums.PaymentStatus;
import com.example.QRCodeGenerationPaymentAPI.enums.Recurrence;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
public class ScheduledPayment {

    @Id
    private String id;
    private String userId;
    private String merchantId;
    private Double amount;
    private String currency;
    private LocalDateTime scheduledDate;
    private PaymentStatus status;

    @Enumerated(EnumType.STRING)
    private Recurrence recurrence;
}
