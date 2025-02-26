package com.example.QRCodeGenerationPaymentAPI.repository;

import com.example.QRCodeGenerationPaymentAPI.model.MerchantBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;

@Repository
public interface MerchantBalanceRepository extends JpaRepository<MerchantBalance, String> {

    @Modifying
    @Transactional
    @Query("UPDATE MerchantBalance m SET m.amount = m.amount + :amount WHERE m.merchantId = :merchantId")
    void updateMerchantBalance(@Param("merchantId") String merchantId, @Param("amount") Double amount);
}