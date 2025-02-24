package com.example.QRCodeGenerationPaymentAPI.exceptions;


    public class InvalidPaymentRequestException extends RuntimeException {
        public InvalidPaymentRequestException(String message) {
            super(message);
        }
    }

