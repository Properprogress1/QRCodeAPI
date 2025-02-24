//package com.example.QRCodeGenerationPaymentAPI.utils;
//
//import com.example.QRCodeGenerationPaymentAPI.model.Transaction;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class QRCodeGeneratorTest {
//
//    private final QRCodeGenerator qrCodeGenerator = new QRCodeGenerator();
//
//    @Test
//    void createQRContent_ValidTransaction_ReturnsFormattedString() {
//        // Arrange
//        Transaction transaction = new Transaction();
//        transaction.setId("TRANS123");
//        transaction.setAmount(100.0);
//        transaction.setCurrency("NGN");
//        transaction.setMerchantId("MERCH123");
//        transaction.setDescription("Test payment");
//
//
//        String qrContent = qrCodeGenerator.createQRContent(transaction);
//
//
//        assertTrue(qrContent.contains("amount=100.0"));
//        assertTrue(qrContent.contains("currency=NGN"));
//        assertTrue(qrContent.contains("merchantId=MERCH123"));
//        assertTrue(qrContent.contains("transactionId=TRANS123"));
//    }
//
//    @Test
//    void generateQRCode_ValidContent_ReturnsBase64String() {
//
//        String content = "Test content";
//
//        String qrCode = qrCodeGenerator.generateQRCode(content);
//
//        assertNotNull(qrCode);
//        assertTrue(qrCode.length() > 0);
//    }
//}
