package com.example.QRCodeGenerationPaymentAPI.serviceImpl;

import com.example.QRCodeGenerationPaymentAPI.dto.TransactionDto;
import com.example.QRCodeGenerationPaymentAPI.enums.PaymentStatus;
import com.example.QRCodeGenerationPaymentAPI.model.Transaction;
import com.example.QRCodeGenerationPaymentAPI.repository.TransactionRepository;
import com.example.QRCodeGenerationPaymentAPI.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionDto transaction(TransactionDto request) {
        // Check if transaction with same id already exists
        if (transactionRepository.existsById(request.getTransactionId())) {
            throw new RuntimeException("Transaction already exists with this ID");
        }

        // Create and save transaction
        Transaction transaction = Transaction.builder()
                .currency(request.getCurrency())
                .amount(request.getAmount())
                .build();

        Transaction savedTransaction = transactionRepository.save(transaction);

        return new TransactionDto(
                savedTransaction.getId(),
                savedTransaction.getAmount(),
                savedTransaction.getCurrency()
        );
    }

    @Override
    public TransactionDto getTransactionDetails(TransactionDto request) {
        Transaction transaction = transactionRepository.findById(request.getTransactionId())
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        return new TransactionDto(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getCurrency()
        );
    }

    @Override
    public TransactionDto refundTransaction(String transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        return new TransactionDto(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getCurrency()
        );
    }

    @Override
    public List<TransactionDto> getTransactions(String userId, String merchantId, PaymentStatus status) {
        List<Transaction> transactions = transactionRepository.findAll(); // You should filter based on inputs

        // Filter the transactions if needed
        return transactions.stream()
                .filter(t -> (userId == null || userId.equals(t.getUserId())) &&
                        (merchantId == null || merchantId.equals(t.getMerchantId())) &&
                        (status == null || status.equals(t.getStatus())))
                .map(t -> new TransactionDto(
                        t.getId(),
                        t.getAmount(),
                        t.getCurrency()
                ))
                .toList();
    }


}