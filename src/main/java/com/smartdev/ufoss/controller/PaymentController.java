package com.smartdev.ufoss.controller;

import com.smartdev.ufoss.dto.PaymentDTO;
import com.smartdev.ufoss.dto.PaymentGetDTO;
import com.smartdev.ufoss.service.PaymentSevice;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/payment")
@AllArgsConstructor
public class PaymentController {

    @Autowired
    private final PaymentSevice paymentSevice;

    @GetMapping("")
    public List<PaymentGetDTO> getAllPayments() {
        return paymentSevice.getAllPayments();
    }

    @GetMapping("/usernameid/{usernameid}")
    public List<PaymentGetDTO> getPaymentByUsername(@PathVariable("usernameid") UUID usernameId){
        return paymentSevice.getPaymentByUsernameID(usernameId);
    }

    @GetMapping("/{paymentid}")
    public PaymentGetDTO getPaymentById(@PathVariable("paymentid") UUID paymentId){
        return  paymentSevice.getPaymentById(paymentId);
    }

    @PostMapping
    public List<PaymentGetDTO> addNewPayment(@RequestBody PaymentDTO newPayment){
        return paymentSevice.addNewPayment(newPayment);
    }

}
