package com.example.QRCodeGenerationPaymentAPI.repository;

import com.example.QRCodeGenerationPaymentAPI.model.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBalanceRepository extends JpaRepository<UserBalance, String> {
}
