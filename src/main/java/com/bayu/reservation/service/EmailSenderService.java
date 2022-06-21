package com.bayu.reservation.service;

public interface EmailSenderService {

    void sendEmail(String to, String body, String subject);
}
