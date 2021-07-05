package com.smartdev.ufoss.service;

import com.smartdev.ufoss.dto.PaymentDTO;
import com.smartdev.ufoss.dto.PaymentDTOGet;
import com.smartdev.ufoss.entity.PaymentEntity;

import java.util.List;
import java.util.UUID;

public interface PaymentSevice {
    public List<PaymentDTOGet> getAllPayments ();
    public PaymentDTOGet getPaymentById(UUID id);
    public List<PaymentDTOGet> getPaymentByUsernameID(UUID id);
    public List<PaymentDTOGet> addNewPayment(PaymentDTO newPayment);
}
