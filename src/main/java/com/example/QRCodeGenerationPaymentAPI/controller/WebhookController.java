package com.example.QRCodeGenerationPaymentAPI.controller;

import com.example.QRCodeGenerationPaymentAPI.apiResponse.ApiResponse;
import com.example.QRCodeGenerationPaymentAPI.dto.PaymentEventDto;
import com.example.QRCodeGenerationPaymentAPI.service.WebhookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequestMapping("/api/v1/webhooks")
    public class WebhookController {
        private final WebhookService webhookService;
        @Autowired
        public WebhookController(WebhookService webhookService) {
            this.webhookService = webhookService;
        }

        @PostMapping("/payment-completed")
        public ResponseEntity<ApiResponse<String>> handlePaymentCompleted(@RequestBody PaymentEventDto event) {
            webhookService.processPaymentCompleted(event);
            return ResponseEntity.ok(new ApiResponse<>("success", "Payment completed event received", null));
        }


        @PostMapping
        public ResponseEntity<String> handleWebhook(@RequestBody String payload) {
            webhookService.processWebhook(payload);
            return ResponseEntity.ok("Webhook received successfully");
        }

        @PostMapping("/payment-failed")
        public ResponseEntity<ApiResponse<String>> handlePaymentFailed(@RequestBody PaymentEventDto event) {
            webhookService.processPaymentFailed(event);
            return ResponseEntity.ok(new ApiResponse<>("failed", "Payment failed event received", null));
        }
    }

