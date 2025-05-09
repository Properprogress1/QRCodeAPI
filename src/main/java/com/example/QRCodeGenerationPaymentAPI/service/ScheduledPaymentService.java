package com.example.QRCodeGenerationPaymentAPI.service;

import com.example.QRCodeGenerationPaymentAPI.dto.ScheduledPaymentDto;

public interface ScheduledPaymentService {
    void createScheduledPayment(ScheduledPaymentDto request);
}

