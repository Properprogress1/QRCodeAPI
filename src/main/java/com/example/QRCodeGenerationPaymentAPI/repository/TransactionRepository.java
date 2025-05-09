package com.example.QRCodeGenerationPaymentAPI.repository;

import com.example.QRCodeGenerationPaymentAPI.enums.PaymentStatus;
import com.example.QRCodeGenerationPaymentAPI.model.Transaction;
import com.example.QRCodeGenerationPaymentAPI.model.User;
import jakarta.persistence.ManyToOne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    boolean existsById(String transactionId);
    Optional<Transaction> findById(String TransactionId);


    @Query("SELECT t FROM Transaction t WHERE " +
            "(:userId IS NULL OR t.userId = :userId) AND " +
            "(:merchantId IS NULL OR t.merchantId = :merchantId) AND " +
            "(:status IS NULL OR t.status = :status) AND " +
            "(:from IS NULL OR t.createdAt >= :from) AND " +
            "(:to IS NULL OR t.createdAt <= :to)")
    List<Transaction> findByFilters(@Param("userId") String userId,
                                    @Param("merchantId") String merchantId,
                                    @Param("status") PaymentStatus status,
                                    @Param("from") LocalDateTime from,
                                    @Param("to") LocalDateTime to);


}