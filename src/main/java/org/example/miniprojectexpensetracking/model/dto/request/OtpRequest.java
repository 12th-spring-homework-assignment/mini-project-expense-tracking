package org.example.miniprojectexpensetracking.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtpRequest {
    private String otpCode;
    private LocalDateTime issuedAt;
    private LocalDateTime expiration;
    private Boolean verify;
    private Integer userId;
}
