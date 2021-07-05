package com.smartdev.ufoss.controller;

import com.smartdev.ufoss.dto.PaymentDTO;
import com.smartdev.ufoss.dto.PaymentDTOGet;
import com.smartdev.ufoss.entity.PaymentEntity;
import com.smartdev.ufoss.service.PaymentSevice;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public List<PaymentDTOGet> getAllPayments() {
        return paymentSevice.getAllPayments();
    }

    @GetMapping("/payments/{usernameid}")
    public List<PaymentDTOGet> getPaymentByUsername(@PathVariable("usernameid") UUID usernameId){
        return paymentSevice.getPaymentByUsernameID(usernameId);
    }

    @GetMapping("/payments/payment/{paymentid}")
    public PaymentDTOGet getPaymentById(@PathVariable("paymentid") UUID paymentId){
        return  paymentSevice.getPaymentById(paymentId);
    }

    @PostMapping("/payments")
    public List<PaymentDTOGet> addNewPayment(@RequestBody PaymentDTO newPayment){
        return paymentSevice.addNewPayment(newPayment);
    }

}
