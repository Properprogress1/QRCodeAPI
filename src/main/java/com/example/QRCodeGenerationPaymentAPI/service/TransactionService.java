package com.example.QRCodeGenerationPaymentAPI.service;

import com.example.QRCodeGenerationPaymentAPI.dto.TransactionDto;
import com.example.QRCodeGenerationPaymentAPI.enums.PaymentStatus;
import com.example.QRCodeGenerationPaymentAPI.model.Transaction;

import java.time.LocalDateTime;
import java.util.List;


public interface TransactionService {
    TransactionDto getTransactionDetails (TransactionDto request);
    TransactionDto refundTransaction (String request);

    List<TransactionDto> getTransactions(String userId, String merchantId, PaymentStatus status);

    List<TransactionDto> getTransactions(String userId, String merchantId, PaymentStatus status, String startDate, String endDate);

    byte[] exportTransactionsReport(String userId, String merchantId, LocalDateTime startDate, LocalDateTime endDate);

//    TransactionDto transaction(TransactionDto request);

    List<Transaction> getTransactions(String userId, String merchantId, PaymentStatus status, LocalDateTime startDate, LocalDateTime endDate);
}
