package com.example.QRCodeGenerationPaymentAPI.service;

import com.example.QRCodeGenerationPaymentAPI.dto.PaymentEventDto;
import org.springframework.stereotype.Service;

@Service
public interface WebhookService {
    void processPaymentCompleted(PaymentEventDto event);

    void processPaymentFailed(PaymentEventDto event);

    void processWebhook(String payload);
}
