package com.smartdev.ufoss.service;

import com.smartdev.ufoss.entity.UserEntity;

public interface EmailSenderService {
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
    ) ;


}
