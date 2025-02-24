package com.example.QRCodeGenerationPaymentAPI.repository;

import com.example.QRCodeGenerationPaymentAPI.model.MerchantBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantBalanceRepository extends JpaRepository<MerchantBalance, String> {

    void updateMerchantBalance(String merchantId, Double amount);
}
