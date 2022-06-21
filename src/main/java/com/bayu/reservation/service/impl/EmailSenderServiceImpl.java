package com.bayu.reservation.service.impl;

import com.bayu.reservation.service.EmailSenderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmailSenderServiceImpl implements EmailSenderService {

    @Override
    public void sendEmail(String to, String body, String subject) {

    }
}
