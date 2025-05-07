package com.example.QRCodeGenerationPaymentAPI.serviceImpl;

import com.example.QRCodeGenerationPaymentAPI.dto.PaymentEventDto;
import com.example.QRCodeGenerationPaymentAPI.service.WebhookService;
import org.springframework.stereotype.Service;

@Service
public class WebhookServiceImpl implements WebhookService {
    @Override
    public void processPaymentCompleted(PaymentEventDto event) {

    }
    public void processWebhook(String payload) {
        // You can parse JSON payload, log, or process it here
        System.out.println("Received Webhook Payload: " + payload);
    }


    @Override
    public void processPaymentFailed(PaymentEventDto event) {

    }
}





