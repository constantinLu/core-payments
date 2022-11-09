package com.orange.corepayments.service;

import com.orange.corepayments.model.Payment;
import com.orange.corepayments.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> findPayments() {
        return paymentRepository.findAll();
    }

    //SHOULD BE WITH STATUS AUTHORIZED
    public Payment authorizePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    //SHOULD BE WITH STATUS CONFIRMED
    public Payment confirmPayment(Payment payment) {
        return paymentRepository.updatePaymentStatus(payment.getId(), payment.getPaymentStatus().getId());
    }
}
