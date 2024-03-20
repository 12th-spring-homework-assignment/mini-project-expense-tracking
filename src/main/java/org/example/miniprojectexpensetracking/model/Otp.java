package org.example.miniprojectexpensetracking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Otp {
    private Integer otpId;
    private String otpCode;
    private LocalDateTime issuedAt;
    private LocalDateTime expiration;
    private Boolean verify;
}
