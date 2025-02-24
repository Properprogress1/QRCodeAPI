package com.example.QRCodeGenerationPaymentAPI.exceptions;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String message) {
        super(message + ": Deposit and try again");
    }
}
