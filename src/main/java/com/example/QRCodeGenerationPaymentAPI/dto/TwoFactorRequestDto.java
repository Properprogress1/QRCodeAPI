package com.example.QRCodeGenerationPaymentAPI.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TwoFactorRequestDto {
    public String getPhoneNum() {
        return this.getPhoneNum();
    }
}
