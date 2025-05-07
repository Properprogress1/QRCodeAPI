package com.example.QRCodeGenerationPaymentAPI.repository;

import com.example.QRCodeGenerationPaymentAPI.model.Transaction;
import com.example.QRCodeGenerationPaymentAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    boolean existsById(String transactionId);
    Optional<Transaction> findById(String TransactionId);
}