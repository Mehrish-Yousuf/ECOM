package com.ecom.paymentservice.ServiceImpl;

import com.ecom.paymentservice.Model.Payment;
import com.ecom.paymentservice.Service.PaymentService;
import com.ecom.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl  implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }
}
