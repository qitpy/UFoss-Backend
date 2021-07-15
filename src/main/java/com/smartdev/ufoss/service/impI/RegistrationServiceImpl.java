package com.smartdev.ufoss.service.impI;

import com.smartdev.ufoss.entity.RoleEntity;
import com.smartdev.ufoss.exception.HandleException;
import com.smartdev.ufoss.model.RegistrationRequest;
import com.smartdev.ufoss.entity.ConfirmationToken;
import com.smartdev.ufoss.entity.UserEntity;
import com.smartdev.ufoss.repository.ConfirmationTokenRepository;
import com.smartdev.ufoss.repository.RoleRepository;
import com.smartdev.ufoss.repository.UserRepository;
import com.smartdev.ufoss.service.EmailSenderService;
import com.smartdev.ufoss.service.RegistrationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final ApplicationUserServiceImpl applicationUserService;

    private final ConfirmationTokenServiceImpl confirmationTokenService;

    private final EmailSenderService emailSenderService;

    private final RoleRepository roleRepository;

    private final ConfirmationTokenRepository confirmationTokenRepository;

    private final UserRepository userRepository;

    @Autowired
    public RegistrationServiceImpl(ApplicationUserServiceImpl applicationUserService, ConfirmationTokenServiceImpl confirmationTokenService, EmailSenderService emailSenderService, RoleRepository roleRepository, UserRepository userRepository, ConfirmationTokenRepository confirmationTokenRepository) {
        this.applicationUserService = applicationUserService;
        this.confirmationTokenService = confirmationTokenService;
        this.emailSenderService = emailSenderService;
        this.roleRepository = roleRepository;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String register(RegistrationRequest request) {

        /*if (!Validator.emailValidate(request.getEmail())) {
            throw new IllegalStateException("email not valid");
        }*/

        Set<RoleEntity> roles = new HashSet<>();
        roles.add(roleRepository.findByName("USER").get());

        String token = applicationUserService.signUpUser(
                new UserEntity(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPhone(),
                        request.getUsername(),
                        request.getPassword(),
                        roles
                )
        );

        String subjectEmail = "Register account UFoss";
        String link = "https://ufoss-intern.herokuapp.com/api/auth/register/confirm?token=" + token;

        emailSenderService.email(
                request.getEmail(),
                subjectEmail,
                buildEmail(request.getFirstName(), link));

        return token;
    }

    @Transactional
    @Override
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("Token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("Email already confirmed");
        }

        LocalDateTime expireAt = confirmationToken.getExpiresAt();

        if (expireAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        applicationUserService.enableApplicationUser(
                confirmationToken.getUserEntity().getEmail());
        return "confirmed";
    }

    @Transactional
    @Override
    public String resendMail(String email) {

        String token = UUID.randomUUID().toString();
        try {
            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity.getIsEnabled()) throw new HandleException("email was registered!");

            ConfirmationToken confirmationToken = confirmationTokenRepository.findByEmail(email).get();
            confirmationToken.setToken(token);
            confirmationToken.setCreateAt(LocalDateTime.now());
            confirmationToken.setExpiresAt(LocalDateTime.now().plusMinutes(1));
            confirmationTokenService.saveConfirmationToken(confirmationToken);
        } catch (Exception e) {
            throw new HandleException("email not found");
        }

        String subjectEmail = "Register account UFoss";
        String link = "https://ufoss-smd.herokuapp.com/api/auth/register/confirm?token=" + token;

        emailSenderService.email(
                email,
                subjectEmail,
                buildEmail("", link));

        return token;
    }

    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 1 minutes. <p>FossDev-team,</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }
}
