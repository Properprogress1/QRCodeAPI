package com.example.QRCodeGenerationPaymentAPI.model;

import com.example.QRCodeGenerationPaymentAPI.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    private String id;
    private String password;
    private String userId;
    private String name;
    private Double balance;
    private String phoneNum;
    private String email;
    private String description;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
