package com.orange.corepayments.controller;

import client.PaymentResponse;
import com.orange.corepayments.model.Payment;
import com.orange.corepayments.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    //TODO: ASYNC -> AUTHORIZATION OPERATION
    // THIS happens when the trip is picked up by the first driver
    // rouber -> update request to payments ->
    // STATUS UPDATED: NONE -> PENDING_AUTHORIZATION -> PENDING_CONFIRMATION -> // TERMINAL: SUCCESS / FAIL

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentResponse authorizePayment(@RequestBody PaymentResponse paymentRequest) {
        return new PaymentResponse(paymentService.authorizePayment(paymentRequest.getPayment()));
    }


    //TODO: ASYNC -> CONFIRMATION OPERATION
    // THIS happens when the trip is picked up by the first driver
    // rouber -> update request to payments ->
    //
    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentResponse confirmPayment(@RequestBody PaymentResponse paymentRequest) {
        return new PaymentResponse(paymentService.confirmPayment(paymentRequest.getPayment()));
    }

}
