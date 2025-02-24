//package com.example.QRCodeGenerationPaymentAPI.service;
//
//import com.example.QRCodeGenerationPaymentAPI.dto.PaymentRequestDto;
//import com.example.QRCodeGenerationPaymentAPI.dto.QRCodeResponseDto;
//import com.example.QRCodeGenerationPaymentAPI.model.Transaction;
//import com.example.QRCodeGenerationPaymentAPI.repository.TransactionRepository;
//import com.example.QRCodeGenerationPaymentAPI.serviceImpl.PaymentServiceImpl;
//import com.example.QRCodeGenerationPaymentAPI.utils.QRCodeGenerator;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.context.ApplicationEventPublisher;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class PaymentServiceTest {
//
//    @Mock
//    private TransactionRepository transactionRepository;
//
//    @Mock
//    private QRCodeGenerator qrCodeGenerator;
//
//    @Mock
//    private ApplicationEventPublisher eventPublisher;
//
//    @InjectMocks
//    private PaymentServiceImpl paymentService;
//
//    private PaymentRequestDto paymentRequest;
//
//    @BeforeEach
//    void setUp() {
//        paymentRequest = new PaymentRequestDto();
//        paymentRequest.setAmount(100.0);
//        paymentRequest.setCurrency("NGN");
//        paymentRequest.setMerchantId("MERCH123");
//        paymentRequest.setDescription("Test payment");
//    }
//
//    @Test
//    void generateQRCode_ValidRequest_ReturnsQRCodeResponse() {
//        // Arrange
//        when(qrCodeGenerator.generateQRCode(any())).thenReturn("base64EncodedQRCode");
//        when(transactionRepository.save(any())).thenReturn(new Transaction());
//
//        // Act
//        QRCodeResponseDto response = paymentService.generateQRCode(paymentRequest);
//
//        // Assert
//        assertNotNull(response);
//        assertNotNull(response.getTransactionId());
//        assertNotNull(response.getQrCodeBase64());
//        assertEquals("base64EncodedQRCode", response.getQrCodeBase64());
//    }
//}
