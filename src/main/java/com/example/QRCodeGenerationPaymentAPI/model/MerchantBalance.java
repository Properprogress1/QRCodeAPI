package com.example.QRCodeGenerationPaymentAPI.model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
@Data
@Table(name = "merchant_balances")
public class MerchantBalance {
    @Id
    private String merchantId;
    private Double amount;
}
