package com.example.QRCodeGenerationPaymentAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

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

    // Proper constructor assigning fields
    public UserDto(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // You can return null or an empty list unless needed
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null; // or Collections.emptyList();
    }
}
