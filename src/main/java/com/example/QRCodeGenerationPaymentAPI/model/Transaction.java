package com.example.QRCodeGenerationPaymentAPI.model;

import com.example.QRCodeGenerationPaymentAPI.enums.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "transactions")
public class Transaction {
    @Id
    private String id;
    private Double amount;
    private String currency;
    private String merchantId;
    private String userId;
    private String description;
    private boolean refunded;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void setRefunded(boolean b) {
    }
}