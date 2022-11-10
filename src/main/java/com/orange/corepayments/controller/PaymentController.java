package com.orange.corepayments.controller;

import com.orange.corepayments.client.PaymentDto;
import com.orange.corepayments.model.Payment;
import com.orange.corepayments.model.PaymentStatus;
import com.orange.corepayments.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;

import static com.orange.corepayments.converter.Converter.toPayment;
import static com.orange.corepayments.converter.Converter.toPaymentDto;

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
    public PaymentDto processPayment(@RequestBody PaymentDto paymentRequest) {
        switch (paymentRequest.getPaymentStatus().getName()) {
            case PENDING_AUTHORIZATION:
                final var authorizePayment = paymentService.authorizePayment(toPayment(paymentRequest));
                return toPaymentDto(authorizePayment);
            case PENDING_CONFIRMATION:
                final var confirmedPayment = paymentService.confirmPayment(toPayment(paymentRequest));
                return toPaymentDto(confirmedPayment);
            case SUCCEEDED:
            case FAILED:
            default:
                throw new ValidationException("Payment with id: {} cannot be processed");
        }
    }


    //TODO: ASYNC -> CONFIRMATION OPERATION
    // THIS happens when the trip is picked up by the first driver
    // rouber -> update request to payments ->
    //
    @PutMapping
    public PaymentDto confirmPayment(@RequestBody PaymentDto paymentRequest) {
        final var payment = paymentService.confirmPayment(toPayment(paymentRequest));
        return toPaymentDto(payment);
    }
}
