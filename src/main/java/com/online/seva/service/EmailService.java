package com.online.seva.service;

import com.online.seva.domain.Email;

public interface EmailService {
    boolean sendSimpleMessage(Email email);
}
