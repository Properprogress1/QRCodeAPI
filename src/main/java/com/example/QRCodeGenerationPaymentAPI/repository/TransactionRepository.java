package com.example.QRCodeGenerationPaymentAPI.repository;

import com.example.QRCodeGenerationPaymentAPI.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
}