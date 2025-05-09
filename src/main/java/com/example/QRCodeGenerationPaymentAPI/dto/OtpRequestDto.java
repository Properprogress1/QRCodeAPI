package com.example.QRCodeGenerationPaymentAPI.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OtpRequestDto {
    private String userId;
    private String otpCode;
    private String expiry;
    private String phoneNum;

}
