package org.example.miniprojectexpensetracking.service;

import jakarta.mail.MessagingException;

public interface EmailService {
    void sendMail(String optCode, String email) throws MessagingException;
}
