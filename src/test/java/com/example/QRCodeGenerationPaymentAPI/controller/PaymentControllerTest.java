//package com.example.QRCodeGenerationPaymentAPI.controller;
//
//import com.example.QRCodeGenerationPaymentAPI.dto.PaymentRequestDto;
//import com.example.QRCodeGenerationPaymentAPI.dto.QRCodeResponseDto;
//import com.example.QRCodeGenerationPaymentAPI.service.PaymentService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.Getter;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(MockitoExtension.class)
//@WebMvcTest(PaymentController.class)
//public class PaymentControllerTest {
//
//    private MockMvc mockMvc;
//
//    @Mock
//    private PaymentService paymentService;
//
//    @InjectMocks
//    private PaymentController paymentController;
//
//    @Autowired
//    @Getter
//    private ObjectMapper objectMapper;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(paymentController).build();
//    }
//
//    @Test
//    public void generateQRCode_ValidRequest_ReturnsQRCode() throws Exception {
//        // Arrange
//        PaymentRequestDto request = new PaymentRequestDto();
//        request.setAmount(100.0);
//        request.setCurrency("NGN");
//        request.setMerchantId("MERCH123");
//        request.setDescription("Test payment");
//
//        QRCodeResponseDto response = QRCodeResponseDto.builder()
//                .qrCodeBase64("base64EncodedQRCode")
//                .transactionId("TRANS123")
//                .build();
//
//        when(paymentService.generateQRCode(any())).thenReturn(response);
//
//
//        mockMvc.perform(post("/api/v1/payments/generate-qr")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.status").value("success"))
//                .andExpect(jsonPath("$.data.qrCodeBase64").value("base64EncodedQRCode"));
//    }
//}
