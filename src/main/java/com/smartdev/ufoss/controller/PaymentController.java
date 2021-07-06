package com.smartdev.ufoss.controller;

import com.smartdev.ufoss.dto.PaymentDTO;
import com.smartdev.ufoss.dto.PaymentGetDTO;
import com.smartdev.ufoss.service.PaymentSevice;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")

public class PaymentController {

    @Autowired
    private final PaymentSevice paymentSevice;

    @GetMapping("/payments")
    public List<PaymentGetDTO> getAllPayments() {
        return paymentSevice.getAllPayments();
    }

    @GetMapping("/payments/usernameid/{usernameid}")
    public List<PaymentGetDTO> getPaymentByUsername(@PathVariable("usernameid") UUID usernameId){
        return paymentSevice.getPaymentByUsernameID(usernameId);
    }

    @GetMapping("/payments/{paymentid}")
    public PaymentGetDTO getPaymentById(@PathVariable("paymentid") UUID paymentId){
        return  paymentSevice.getPaymentById(paymentId);
    }

    @PostMapping("/payments")
    public List<PaymentGetDTO> addNewPayment(@RequestBody PaymentDTO newPayment){
        return paymentSevice.addNewPayment(newPayment);
    }

}
