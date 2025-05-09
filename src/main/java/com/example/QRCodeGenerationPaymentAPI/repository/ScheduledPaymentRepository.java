package com.example.QRCodeGenerationPaymentAPI.repository;

import com.example.QRCodeGenerationPaymentAPI.model.ScheduledPayment;
import com.example.QRCodeGenerationPaymentAPI.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScheduledPaymentRepository extends JpaRepository<ScheduledPayment, String> {
    List<ScheduledPayment> findByScheduledDateBeforeAndStatus(LocalDateTime dateTime, PaymentStatus status);
}
