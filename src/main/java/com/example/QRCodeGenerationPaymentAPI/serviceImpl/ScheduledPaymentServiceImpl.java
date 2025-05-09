package com.example.QRCodeGenerationPaymentAPI.serviceImpl;

import com.example.QRCodeGenerationPaymentAPI.dto.PaymentRequestDto;
import com.example.QRCodeGenerationPaymentAPI.dto.ScheduledPaymentDto;
import com.example.QRCodeGenerationPaymentAPI.enums.PaymentStatus;
import com.example.QRCodeGenerationPaymentAPI.model.ScheduledPayment;
import com.example.QRCodeGenerationPaymentAPI.repository.ScheduledPaymentRepository;
import com.example.QRCodeGenerationPaymentAPI.service.PaymentService;
import com.example.QRCodeGenerationPaymentAPI.service.ScheduledPaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduledPaymentServiceImpl implements ScheduledPaymentService {

    private final ScheduledPaymentRepository scheduledPaymentRepository;
    private final PaymentService paymentService;

    @Override
    public void createScheduledPayment(ScheduledPaymentDto request) {
        ScheduledPayment payment = ScheduledPayment.builder()
                .id(UUID.randomUUID().toString())
                .userId(request.getUserId())
                .merchantId(request.getMerchantId())
                .amount(request.getAmount())
                .currency(request.getCurrency())
                .scheduledDate(request.getScheduledDate())
                .recurrence(request.getRecurrence())
                .status(PaymentStatus.PENDING)
                .build();
        scheduledPaymentRepository.save(payment);
    }

    @Scheduled(cron = "0 0 * * * *") // Run hourly
    public void processScheduledPayments() {
        List<ScheduledPayment> payments = scheduledPaymentRepository.findByScheduledDateBeforeAndStatus(
                LocalDateTime.now(), PaymentStatus.PENDING);
        payments.forEach(payment -> {
            PaymentRequestDto request = mapToPaymentRequestDto(payment);
            String transactionId = paymentService.generateQRCode(request).getTransactionId();
            paymentService.processPayment(transactionId); // Add OTP if required in your flow
            if (payment.getRecurrence() != null) {
                updateNextScheduledDate(payment);
            } else {
                payment.setStatus(PaymentStatus.COMPLETED);
            }
            scheduledPaymentRepository.save(payment);
        });
    }

    private PaymentRequestDto mapToPaymentRequestDto(ScheduledPayment payment) {
        return PaymentRequestDto.builder()
                .userId(payment.getUserId())
                .merchantId(payment.getMerchantId())
                .amount(payment.getAmount())
                .currency(payment.getCurrency())
                .description("Scheduled Payment")
                .build();
    }

    private void updateNextScheduledDate(ScheduledPayment payment) {
        if (payment.getRecurrence() == null) {
            throw new IllegalStateException("Recurrence is null for payment ID: " + payment.getId());
        }

        switch (payment.getRecurrence()) {
            case DAILY -> payment.setScheduledDate(payment.getScheduledDate().plusDays(1));
            case WEEKLY -> payment.setScheduledDate(payment.getScheduledDate().plusWeeks(1));
            case MONTHLY -> payment.setScheduledDate(payment.getScheduledDate().plusMonths(1));
            default -> throw new IllegalArgumentException("Unsupported recurrence: " + payment.getRecurrence());
        }
    }


}
