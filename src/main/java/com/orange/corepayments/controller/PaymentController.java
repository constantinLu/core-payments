package com.orange.corepayments.controller;

import com.orange.corepayments.client.CorePaymentDto;
import com.orange.corepayments.client.PaymentDto;
import com.orange.corepayments.model.Payment;
import com.orange.corepayments.service.PaymentService;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.orange.corepayments.client.PaymentStatusType.PENDING_AUTHORIZATION;
import static com.orange.corepayments.client.PaymentStatusType.UNPROCESSED;
import static com.orange.corepayments.converter.Converter.toPayment;
import static com.orange.corepayments.converter.Converter.toPaymentDto;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping()
    public List<Payment> readPayments() {
        return paymentService.findPayments();
    }

    @PostMapping
    public PaymentDto processPayment(@RequestBody CorePaymentDto paymentRequest) {
        Assert.isTrue(paymentRequest.getPaymentStatus().equals(UNPROCESSED), "Payment can only be UNPROCESSED");
        final var authorizePayment = paymentService.authorizePayment(toPayment(paymentRequest));
        return toPaymentDto(authorizePayment);
    }

    @PutMapping
    public PaymentDto confirmPayment(@RequestBody PaymentDto paymentRequest) {
        Assert.isTrue(paymentRequest.getPaymentStatus().equals(PENDING_AUTHORIZATION), "Payment can only be CONFIRMED");
        final var payment = paymentService.confirmPayment(toPayment(paymentRequest));
        return toPaymentDto(payment);
    }
}
