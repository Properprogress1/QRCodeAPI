package com.example.QRCodeGenerationPaymentAPI.service;

import com.example.QRCodeGenerationPaymentAPI.dto.TransactionDto;
import com.example.QRCodeGenerationPaymentAPI.enums.PaymentStatus;

import java.util.List;


public interface TransactionService {
    TransactionDto getTransactionDetails (TransactionDto request);
    TransactionDto refundTransaction (String request);

    List<TransactionDto> getTransactions(String userId, String merchantId, PaymentStatus status);
}
