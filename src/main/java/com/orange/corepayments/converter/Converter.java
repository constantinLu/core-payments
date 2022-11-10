package com.orange.corepayments.converter;

import com.orange.corepayments.client.PaymentDto;
import com.orange.corepayments.model.Payment;

public class Converter {

    public static Payment toPayment(PaymentDto paymentDto) {
        return Payment.builder()
                .amount(paymentDto.getAmount())
                .reward(paymentDto.getReward())
                .reason(paymentDto.getReason().orElse(null))
                .paymentStatus(paymentDto.getPaymentStatus())
                .build();
    }

    public static PaymentDto toPaymentDto(Payment payment) {
        return PaymentDto.builder()
                .amount(payment.getAmount())
                .reward(payment.getReward())
                .reason(payment.getReason())
                .paymentStatus(payment.getPaymentStatus())
                .build();
    }
}
