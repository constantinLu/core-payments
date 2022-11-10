package com.orange.corepayments.client;

import com.orange.corepayments.model.PaymentStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Getter
@Builder
@Value
public class PaymentDto {

    BigDecimal amount;

    Optional<String> reason;

    BigDecimal reward;

    UUID requestId;

    PaymentStatus paymentStatus;

    LocalDateTime updatedDate;

    LocalDateTime createdDate;
}
