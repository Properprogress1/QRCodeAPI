package com.example.QRCodeGenerationPaymentAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories("com.example.QRCodeGenerationPaymentAPI.repository")
public class QrCodeGenerationPaymentApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(QrCodeGenerationPaymentApiApplication.class, args);
	}

}



