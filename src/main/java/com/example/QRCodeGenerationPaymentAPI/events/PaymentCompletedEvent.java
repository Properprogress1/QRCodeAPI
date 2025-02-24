package com.example.QRCodeGenerationPaymentAPI.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class PaymentCompletedEvent extends ApplicationEvent {
    private final String transactionId;
    private final String merchantId;
    private final Double amount;

    public PaymentCompletedEvent(Object source, String transactionId, String merchantId, Double amount) {
        super(source);
        this.transactionId = transactionId;
        this.merchantId = merchantId;
        this.amount = amount;
    }
}
