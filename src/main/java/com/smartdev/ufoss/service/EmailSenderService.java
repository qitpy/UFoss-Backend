package com.smartdev.ufoss.service;

public interface EmailSenderService {
    //method send be rename to email
    public void email(
            String to,
            String subject,
            String email
    );

    public void emailResetPassword(
            String emailRecipient,
            String subject,
            String nameRecipient,
            String link,
            String titleLink
    );
}
