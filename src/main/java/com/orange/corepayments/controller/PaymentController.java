package com.orange.corepayments.controller;

import com.orange.corepayments.PaymentService;
import com.orange.corepayments.model.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    //TODO: Add validation to DTOS
    //TODO: test endponint (REMOVE IT IN THE FINAL STAGES)
    @GetMapping()
    public List<Payment> readPayments() {
        return paymentService.findPayments();
    }
}
