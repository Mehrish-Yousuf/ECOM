package com.ecom.paymentservice.Service;

import com.ecom.paymentservice.DTO.PaymentDTO;
import com.ecom.paymentservice.Model.Payment;

public interface PaymentService {
    public Payment savePayment(PaymentDTO payment) throws Exception;


}
