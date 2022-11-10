package com.orange.corepayments.service;

import com.orange.corepayments.model.Payment;
import com.orange.corepayments.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.orange.corepayments.model.PaymentStatus.Id.PENDING_AUTHORIZATION;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> findPayments() {
        return paymentRepository.findAll();
    }

    public Payment authorizePayment(Payment payment) {
        assert payment.getPaymentStatus().getName().name().equals(PENDING_AUTHORIZATION.name());
        return paymentRepository.save(payment);
    }

    public Payment confirmPayment(Payment payment) {
        payment.setPaymentStatus(payment.getPaymentStatus());
        return paymentRepository.save(payment);
    }
}
