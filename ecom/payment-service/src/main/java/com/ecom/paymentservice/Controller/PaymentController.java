package com.ecom.paymentservice.Controller;

import com.ecom.paymentservice.DTO.PaymentDTO;
import com.ecom.paymentservice.Model.Payment;
import com.ecom.paymentservice.Service.PaymentService;
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
    private PaymentService paymentService;

    @Autowired
    private HeaderGenerator headerGenerator;



    @PostMapping("/add")
    ResponseEntity<Payment>  savePayment(@RequestBody PaymentDTO paymentDTO, HttpServletRequest request) {
        Payment payment = null;

        try {
            payment = paymentService.savePayment(paymentDTO);
            return new ResponseEntity<Payment>(
                    payment,
                    headerGenerator.getHeadersForSuccessGetMethod(),
                    HttpStatus.CREATED);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<Payment>(
                    headerGenerator.getHeadersForError(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

