package com.smartdev.ufoss.controller;

import com.smartdev.ufoss.dto.PaymentDTO;
import com.smartdev.ufoss.dto.PaymentDTOGet;
import com.smartdev.ufoss.entity.PaymentEntity;
import com.smartdev.ufoss.service.PaymentSevice;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")

public class PaymentController {

    private final PaymentSevice paymentSevice;

    @GetMapping("/payments")
    public List<PaymentDTOGet> getAllPayments() {
        return paymentSevice.getAllPayments();
    }
    @GetMapping("/payments/{usernameid}")
    public PaymentDTOGet getPaymentByUsername(@PathVariable("usernameid") String usernameId){
        System.out.println(paymentSevice.getPaymentByUsernameID(usernameId));
        return paymentSevice.getPaymentByUsernameID(usernameId);
    }
    @PostMapping("/payments")
    public PaymentEntity addNewPayment(@RequestBody PaymentDTO newPayment){
        return paymentSevice.addNewPayment(newPayment);
    }

}
