package com.smartdev.ufoss.service.impl;

import com.smartdev.ufoss.entity.UserEntity;
import com.smartdev.ufoss.service.EmailSenderService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class EmailSenderServiceServiceImpl implements EmailSenderService {

    private final static Logger LOGGER = (Logger) LoggerFactory
            .getLogger(EmailSenderServiceServiceImpl.class);
    private final JavaMailSender mailSender;

    public void emailResetPassword(
            String emailRecipient,
            String subject,
            String nameRecipient,
            String link,
            String titleLink
    ) {

        email(emailRecipient, subject, buildEmail(nameRecipient, link, titleLink));

    }

    @Override
    @Async
    public void email(String emailRecipient,String subject, String emailContent) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(emailContent, true);
            helper.setTo(emailRecipient);
            helper.setSubject(subject);
            helper.setFrom("FossDev with ♥ <Fossdev@hotmail.com>");
            mailSender.send(mimeMessage);
        } catch(MessagingException e) {
            LOGGER.error("failed to send email", e);
            throw new IllegalStateException("failed to send email");
        }
    }

    public static String buildEmail(
            String nameRecipient,
            String link,
            String titleLink
    ) {
        return "Hello " + nameRecipient + ",<br>" +
                "Click on the link below to reset the password.<br>" +
                "Please do not reply to this email. <br><br>" +
                "<button style = 'background-color: #EC5252;'>" +
                "<a href = '" + link + "' style = 'margin:20px 20px; font-size: 24px; font-weigh: bold; color: white;' >" + titleLink + "</a >" +
                "</button> <br><br>" +
                "Thank you.<br>" +
                "FossDev - Team.";
    }
}
