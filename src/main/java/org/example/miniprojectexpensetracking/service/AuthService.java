package org.example.miniprojectexpensetracking.service;

import jakarta.mail.MessagingException;
import org.example.miniprojectexpensetracking.model.dto.request.AppUserRequest;
import org.example.miniprojectexpensetracking.model.dto.request.AuthRequest;
import org.example.miniprojectexpensetracking.model.dto.request.PasswordRequest;
import org.example.miniprojectexpensetracking.model.dto.response.AppUserResponse;
import org.example.miniprojectexpensetracking.model.dto.response.AuthResponse;

import java.util.UUID;

public interface AuthService {
    AuthResponse login(AuthRequest authRequest) throws Exception;

    AppUserResponse register(AppUserRequest appUserRequest) throws MessagingException;

    void verify(String optCode);

    void resend(String email) throws MessagingException;

    void forget(String email, PasswordRequest passwordRequest);

    UUID findCurrentUser();
}
