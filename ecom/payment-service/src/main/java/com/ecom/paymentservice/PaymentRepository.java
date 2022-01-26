package com.ecom.paymentservice;

import com.ecom.paymentservice.Model.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
}
