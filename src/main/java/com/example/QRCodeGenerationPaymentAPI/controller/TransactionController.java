package com.example.QRCodeGenerationPaymentAPI.controller;

import com.example.QRCodeGenerationPaymentAPI.apiResponse.ApiResponse;
import com.example.QRCodeGenerationPaymentAPI.dto.TransactionDto;
import com.example.QRCodeGenerationPaymentAPI.enums.PaymentStatus;
import com.example.QRCodeGenerationPaymentAPI.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    // Basic filter: userId, merchantId, status
    @GetMapping
    public ResponseEntity<ApiResponse<List<TransactionDto>>> getTransactions(
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String merchantId,
            @RequestParam(required = false) PaymentStatus status) {

        List<TransactionDto> transactions = transactionService.getTransactions(userId, merchantId, status);
        return ResponseEntity.ok(new ApiResponse<>("success", "Transactions retrieved successfully", transactions));
    }

    // Extended search with date range
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<TransactionDto>>> searchTransactions(
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String merchantId,
            @RequestParam(required = false) PaymentStatus status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String endDate) {

        List<TransactionDto> transactions = transactionService.getTransactions(userId, merchantId, status, startDate, endDate);
        return ResponseEntity.ok(new ApiResponse<>("success", "Filtered transactions retrieved", transactions));
    }

    @PostMapping("/{transactionId}/refund")
    public ResponseEntity<ApiResponse<TransactionDto>> refundTransaction(@PathVariable String transactionId) {
        TransactionDto transaction = transactionService.refundTransaction(transactionId);
        return ResponseEntity.ok(new ApiResponse<>("success", "Transaction refunded successfully", transaction));
    }

    @GetMapping("/report")
    public ResponseEntity<byte[]> exportTransactionsReport(
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String merchantId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        byte[] report = transactionService.exportTransactionsReport(userId, merchantId, startDate, endDate);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=transactions_report.csv")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(report);
    }
}