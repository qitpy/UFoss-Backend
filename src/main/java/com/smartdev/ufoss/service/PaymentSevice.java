package com.smartdev.ufoss.service;

import com.smartdev.ufoss.dto.PaymentDTO;
import com.smartdev.ufoss.dto.PaymentGetDTO;

import java.util.List;
import java.util.UUID;

public interface PaymentSevice {
    public List<PaymentGetDTO> getAllPayments ();
    public PaymentGetDTO getPaymentById(UUID id);
    public List<PaymentGetDTO> getPaymentByUsernameID(UUID id);
    public List<PaymentGetDTO> addNewPayment(PaymentDTO newPayment);
}
