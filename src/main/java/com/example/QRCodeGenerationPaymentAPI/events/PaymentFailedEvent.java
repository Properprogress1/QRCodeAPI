package com.example.QRCodeGenerationPaymentAPI.events;

import lombok.Builder;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class PaymentFailedEvent extends ApplicationEvent {

    private final String transactionId;
    private final String merchantId;
    private final Double amount;
    private final String errorMessage;

    public PaymentFailedEvent(Object source, String transactionId, String merchantId, Double amount, String errorMessage) {
        super(source);
        this.transactionId = transactionId;
        this.merchantId = merchantId;
        this.amount = amount;
        this.errorMessage = errorMessage;
    }
}
