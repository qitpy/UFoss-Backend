package com.smartdev.ufoss.controller;

import com.smartdev.ufoss.component.Validator;
import com.smartdev.ufoss.dto.ResetPassworDTO;
import com.smartdev.ufoss.entity.UserEntity;
import com.smartdev.ufoss.exception.UserNotFoundException;
import com.smartdev.ufoss.service.EmailSenderService;
import com.smartdev.ufoss.service.UserService;
import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@AllArgsConstructor
public class ForgotPasswordController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("/forgot_password")
    public ResponseEntity<?> processForgotPasswordForm(@RequestBody ResetPassworDTO model) {

        if (!Validator.emailValidate(model.getEmail())) {
            return ResponseEntity.ok("Email not validate!");
        }

        String token = RandomString.make(50);

        try {
            UserEntity user = userService.updateResetPassword(token, model.getEmail());
            String subjectEmail = "Email <Reset Password>";

            String resetPasswordLink = "https://www.facebook.com/ngochai20101" + "/reset-password?token=" + token;

            emailSenderService.emailResetPassword(
                    user.getEmail(),
                    subjectEmail,
                    user.getLastName() + " " + user.getFirstName(),
                    resetPasswordLink,
                    "Click to Reset Password!"
            );

        } catch (UserNotFoundException e) {
            return ResponseEntity.ok(e.getMessage());
        }

        return ResponseEntity.ok("check your email");
    }

    @PostMapping("/update_password")
    public ResponseEntity<?> processResetPassword(@RequestBody ResetPassworDTO model) {
        UserEntity user = userService.getUserWithToken(model.getResetPasswordToken());

        if (user != null) {
            user.setPassword(model.getPassword());
            userService.updatePassword(user);

            return ResponseEntity.ok("Successfully");
        }

        return ResponseEntity.ok("user");
    }
}
