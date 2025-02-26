package com.example.QRCodeGenerationPaymentAPI.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user_balances")
public class UserBalance {
    @Id
    private String userId;
    private Double amount;
}