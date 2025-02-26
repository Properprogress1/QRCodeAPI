package com.example.QRCodeGenerationPaymentAPI.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "merchant_balances")
public class MerchantBalance {
    @Id
    private String merchantId;
    private Double amount;
}