package com.example.QRCodeGenerationPaymentAPI.serviceImpl;

import com.example.QRCodeGenerationPaymentAPI.dto.PaymentRequestDto;
import com.example.QRCodeGenerationPaymentAPI.dto.PaymentResponseDto;
import com.example.QRCodeGenerationPaymentAPI.dto.QRCodeResponseDto;
import com.example.QRCodeGenerationPaymentAPI.enums.Currency;
import com.example.QRCodeGenerationPaymentAPI.enums.PaymentStatus;
import com.example.QRCodeGenerationPaymentAPI.events.PaymentCompletedEvent;
import com.example.QRCodeGenerationPaymentAPI.exceptions.InsufficientBalanceException;
import com.example.QRCodeGenerationPaymentAPI.exceptions.InvalidPaymentRequestException;
import com.example.QRCodeGenerationPaymentAPI.exceptions.TransactionNotFoundException;
import com.example.QRCodeGenerationPaymentAPI.model.ScheduledPayment;
import com.example.QRCodeGenerationPaymentAPI.model.Transaction;
import com.example.QRCodeGenerationPaymentAPI.model.User;
import com.example.QRCodeGenerationPaymentAPI.model.UserBalance;
import com.example.QRCodeGenerationPaymentAPI.repository.MerchantBalanceRepository;
import com.example.QRCodeGenerationPaymentAPI.repository.TransactionRepository;
import com.example.QRCodeGenerationPaymentAPI.repository.UserBalanceRepository;
import com.example.QRCodeGenerationPaymentAPI.repository.UserRepository;
import com.example.QRCodeGenerationPaymentAPI.service.OtpService;
import com.example.QRCodeGenerationPaymentAPI.service.PaymentService;
import com.example.QRCodeGenerationPaymentAPI.utils.QRCodeGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final TransactionRepository transactionRepository;
    private final UserBalanceRepository userBalanceRepository;
    private final MerchantBalanceRepository merchantBalanceRepository;
    private final QRCodeGenerator qrCodeGenerator;
    private final ApplicationEventPublisher eventPublisher;
    private final UserRepository userRepository;
    private final OtpService otpService;

    public List<Transaction> findTransactions(String filter1, String filter2, PaymentStatus paymentStatus, LocalDateTime startDate, LocalDateTime endDate) {
        return transactionRepository.findByFilters(filter1, filter2, paymentStatus, startDate, endDate);
    }

    @Override
    public void validatePaymentRequest(PaymentRequestDto request) {
        if (request.getAmount() <= 0) {
            throw new InvalidPaymentRequestException("Amount must be greater than 0");
        }

        try {
            Currency.valueOf(request.getCurrency().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidPaymentRequestException("Invalid currency");
        }

        if (request.getMerchantId() == null || request.getMerchantId().trim().isEmpty()) {
            throw new InvalidPaymentRequestException("Merchant ID is required");
        }
    }

    @Override
    public QRCodeResponseDto getTransactions(QRCodeResponseDto request) {
        return null;
    }

    public String createQRContent(Transaction transaction) {
        return String.format("transactionId:%s;amount:%.2f;currency:%s;merchantId:%s",
                transaction.getId(),
                transaction.getAmount(),
                transaction.getCurrency(),
                transaction.getMerchantId());
    }

    @Override
    public QRCodeResponseDto generateQRCode(PaymentRequestDto request) {
        log.info("Generating QR code for payment request: {}", request);
        validatePaymentRequest(request);

        String transactionId = UUID.randomUUID().toString();
        Transaction transaction = createTransaction(request, transactionId);
        transactionRepository.save(transaction);

        String qrCodeContent = qrCodeGenerator.createQRContent(transaction);
        String qrCodeBase64 = qrCodeGenerator.generateQRCode(qrCodeContent);

        log.info("QR code generated successfully for transaction: {}", transactionId);
        return QRCodeResponseDto.builder()
                .qrCodeBase64(qrCodeBase64)
                .transactionId(transactionId)
                .build();
    }

    @Override
    public PaymentResponseDto processPayment(String transactionId) {
        return null;
    }

//    @Override
//    @Transactional
//    public PaymentResponseDto processPayment(String transactionId, String otp) {
//        log.info("Processing payment for transaction: {}", transactionId);
//        Transaction transaction = getAndValidateTransaction(transactionId);
//
//        if (transaction.getStatus() == PaymentStatus.COMPLETED) {
//            log.warn("Transaction {} has already been completed", transactionId);
//            return createPaymentResponse(transaction);
//        }
//
//        // Get user details
//        User user = userRepository.findById(transaction.getUserId())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        // 2FA logic
//        if (transaction.getAmount() > 1000.0 && user.isTwoFactorEnabled()) {
//            if (!otpService.verifyOtp(user.getUserId(), otp)) {
//                throw new SecurityException("Invalid OTP");
//            }
//        }
//
//        UserBalance userBalance = getUserBalance(transaction.getUserId());
//        validateUserBalance(userBalance, transaction.getAmount());
//
//        try {
//            processBalances(transaction, userBalance);
//
//            eventPublisher.publishEvent(new PaymentCompletedEvent(
//                    this,
//                    transaction.getId(),
//                    transaction.getMerchantId(),
//                    transaction.getAmount()
//            ));
//
//            log.info("Payment processed successfully for transaction: {}", transactionId);
//            return createPaymentResponse(transaction);
//        } catch (Exception e) {
//            log.error("Error processing payment for transaction: {}", transactionId, e);
//            transaction.setStatus(PaymentStatus.FAILED);
//            transactionRepository.save(transaction);
//            throw new RuntimeException("Payment processing failed", e);
//        }
//    }

    @Override
    public PaymentResponseDto getPaymentStatus(String transactionId) {
        Transaction transaction = getAndValidateTransaction(transactionId);
        return createPaymentResponse(transaction);
    }

    private Transaction createTransaction(PaymentRequestDto request, String transactionId) {
        return Transaction.builder()
                .id(transactionId)
                .userId(request.getMerchantId())
                .merchantId(request.getMerchantId())
                .amount(request.getAmount())
                .currency(request.getCurrency())
                .description(request.getDescription())
                .status(PaymentStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    private Transaction getAndValidateTransaction(String transactionId) {
        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found: " + transactionId));
    }

    private UserBalance getUserBalance(String userId) {
        return userBalanceRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User balance not found for ID: " + userId));
    }

    private void validateUserBalance(UserBalance userBalance, Double amount) {
        if (userBalance.getAmount() < amount) {
            throw new InsufficientBalanceException("Insufficient balance");
        }
    }

    @Transactional
    private void processBalances(Transaction transaction, UserBalance userBalance) {
        userBalance.setAmount(userBalance.getAmount() - transaction.getAmount());
        userBalanceRepository.save(userBalance);

        merchantBalanceRepository.updateMerchantBalance(
                transaction.getMerchantId(),
                transaction.getAmount()
        );

        transaction.setStatus(PaymentStatus.COMPLETED);
        transaction.setUpdatedAt(LocalDateTime.now());
        transactionRepository.save(transaction);
    }

    private PaymentResponseDto createPaymentResponse(Transaction transaction) {
        Double merchantBalance = getMerchantBalance(transaction.getMerchantId());
        Double userBalance = getUserBalance(transaction.getUserId()).getAmount();

        return PaymentResponseDto.builder()
                .transactionId(transaction.getId())
                .status(transaction.getStatus().name())
                .userBalance(userBalance)
                .merchantBalance(merchantBalance)
                .build();
    }

    private Double getMerchantBalance(String merchantId) {
        return merchantBalanceRepository.findById(merchantId)
                .orElseThrow(() -> new RuntimeException("Merchant balance not found"))
                .getAmount();
    }


    @Override
    @Transactional
    public PaymentResponseDto processPayment(String transactionId, String otp) {
        log.info("Processing payment for transaction: {}", transactionId);

        // 1. Retrieve and validate transaction
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found: " + transactionId));

        if (transaction.getStatus() == PaymentStatus.COMPLETED) {
            log.warn("Transaction {} has already been completed", transactionId);
            return createPaymentResponse(transaction);
        }

        // 2. Fetch user details
        User user = userRepository.findById(transaction.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 3. Check if OTP is required and verify
        if (transaction.getAmount() > 100000 && user.isTwoFactorEnabled()) {
            boolean isOtpValid = otpService.verifyOtp(user.getUserId(), otp);
            if (!isOtpValid) {
                throw new SecurityException("Invalid OTP");
            }
        }

        // 4. Verify balance and process payment
        UserBalance userBalance = userBalanceRepository.findById(transaction.getUserId())
                .orElseThrow(() -> new RuntimeException("User balance not found"));

        if (userBalance.getAmount() < transaction.getAmount()) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        try {
            // Deduct user balance
            userBalance.setAmount(userBalance.getAmount() - transaction.getAmount());
            userBalanceRepository.save(userBalance);

            // Credit merchant balance
            merchantBalanceRepository.updateMerchantBalance(
                    transaction.getMerchantId(),
                    transaction.getAmount()
            );

            // Mark transaction as completed
            transaction.setStatus(PaymentStatus.COMPLETED);
            transaction.setUpdatedAt(LocalDateTime.now());
            transactionRepository.save(transaction);

            // Publish payment completed event
            eventPublisher.publishEvent(new PaymentCompletedEvent(
                    this,
                    transaction.getId(),
                    transaction.getMerchantId(),
                    transaction.getAmount()
            ));

            log.info("Payment processed successfully for transaction: {}", transactionId);
            return createPaymentResponse(transaction);

        } catch (Exception e) {
            log.error("Error processing payment for transaction: {}", transactionId, e);
            transaction.setStatus(PaymentStatus.FAILED);
            transactionRepository.save(transaction);
            throw new RuntimeException("Payment processing failed", e);
        }
    }

    @Override
    public PaymentRequestDto mapToPaymentRequest(ScheduledPayment payment) {
        return null;
    }

}
