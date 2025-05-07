package com.example.QRCodeGenerationPaymentAPI.controller;

import com.example.QRCodeGenerationPaymentAPI.apiResponse.ApiResponse;
import com.example.QRCodeGenerationPaymentAPI.dto.TransactionDto;
import com.example.QRCodeGenerationPaymentAPI.enums.PaymentStatus;
import com.example.QRCodeGenerationPaymentAPI.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<TransactionDto>>> getTransactions(
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String merchantId,
            @RequestParam(required = false) PaymentStatus status) {
        List<TransactionDto> transactions = transactionService.getTransactions(userId, merchantId, status);
        return ResponseEntity.ok(new ApiResponse<>("success", "Transactions retrieved successfully", transactions));
    }

    @PostMapping("/{transactionId}/refund")
    public ResponseEntity<ApiResponse<TransactionDto>> refundTransaction(@PathVariable String transactionId) {
        TransactionDto transaction = transactionService.refundTransaction(transactionId);
        return ResponseEntity.ok(new ApiResponse<>("success", "Transaction refunded successfully", transaction));
    }
}
