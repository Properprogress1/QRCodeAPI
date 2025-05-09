package com.example.QRCodeGenerationPaymentAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String id;
    private String userId;
    private String name;
    private String phoneNum;
    private String email;
    private String balance;
    private boolean twoFactorEnabled;

    public UserDto(String id, String name, String email) {
    }

    // Optional: For Spring Security compatibility
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    // Custom constructor for specific use case (optional)
    public UserDto(String id, String email, String phoneNum, boolean twoFactorEnabled) {
        this.id = id;
        this.email = email;
        this.phoneNum = phoneNum;
        this.twoFactorEnabled = twoFactorEnabled;
    }
}
