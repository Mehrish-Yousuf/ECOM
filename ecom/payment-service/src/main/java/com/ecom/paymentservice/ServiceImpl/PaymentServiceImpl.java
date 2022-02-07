package com.ecom.paymentservice.ServiceImpl;

import com.ecom.paymentservice.DTO.OrderDTO;
import com.ecom.paymentservice.DTO.PaymentDTO;
import com.ecom.paymentservice.DTO.UserDTO;
import com.ecom.paymentservice.Model.Payment;
import com.ecom.paymentservice.Service.PaymentService;
import com.ecom.paymentservice.feignclient.OrderClient;
import com.ecom.paymentservice.feignclient.UserClient;
import com.ecom.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentServiceImpl  implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderClient orderClient;

    @Autowired
    private UserClient userClient;

    @Override
    public Payment savePayment(PaymentDTO paymentDTO) throws Exception {

        Payment payment = new Payment();
        UserDTO user = userClient.getUserById(paymentDTO.getUserId());
        OrderDTO order = orderClient.getOrderById(paymentDTO.getOrderId());
        payment.setUserId(user.getId());
        payment.setOrderId(order.getId());
        if(order.getTotal().compareTo(BigDecimal.valueOf(paymentDTO.getAmount()))==0){
            payment.setTotalAmount(paymentDTO.getAmount());
        }
        else {
            throw new Exception("The amount is not correct");
        }
        payment.setPaymentMode(paymentDTO.getPaymentMode());

        return paymentRepository.save(payment);
    }
}
