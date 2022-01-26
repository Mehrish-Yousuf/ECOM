package com.ecom.paymentservice.Controller;

import com.ecom.paymentservice.DTO.PaymentDTO;
import com.ecom.paymentservice.Model.Order;
import com.ecom.paymentservice.Model.Payment;
import com.ecom.paymentservice.Model.User;
import com.ecom.paymentservice.Service.PaymentService;
import com.ecom.paymentservice.feignclient.OrderClient;
import com.ecom.paymentservice.feignclient.UserClient;
import com.ecom.paymentservice.header.HeaderGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private OrderClient orderClient;

    @Autowired
    private UserClient userClient;

    @Autowired
    PaymentService paymentService;

    @Autowired
    HeaderGenerator headerGenerator;



    @PostMapping("/add")
    ResponseEntity<Payment>  savePayment(@RequestBody PaymentDTO paymentDTO, HttpServletRequest request) {

        Payment payment = new Payment();
        User user = userClient.getUserById(paymentDTO.getUserId());
        Order order = orderClient.getOrderById(paymentDTO.getOrderId());
        payment.setUser(user);
        payment.setOrder(order);
        payment.setPaymentMode(paymentDTO.getPaymentMode());
        payment.setTotalAmount(paymentDTO.getAmount());

        try {
            paymentService.savePayment(payment);

            return new ResponseEntity<Payment>(
                    payment,
                    headerGenerator.getHeadersForSuccessPostMethod(request, payment.getId()),
                    HttpStatus.CREATED);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<Payment>(
                    headerGenerator.getHeadersForError(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

