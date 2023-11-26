package com.demo.finbank.services;

import com.demo.finbank.dto.EmailDetails;

public interface EmailService {
    void sendEmailAlert(EmailDetails emailDetails);
}
