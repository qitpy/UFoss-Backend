package com.smartdev.ufoss.service;

import com.smartdev.ufoss.dto.PaymentDTO;
import com.smartdev.ufoss.dto.PaymentDTOGet;
import com.smartdev.ufoss.entity.PaymentEntity;

import java.util.List;
import java.util.UUID;

public interface PaymentSevice {
    public List<PaymentDTOGet> getAllPayments ();
    public PaymentEntity getPaymentById(UUID id);
    public PaymentDTOGet getPaymentByUsernameID(String id);
    public PaymentEntity addNewPayment(PaymentDTO newPayment);
}
