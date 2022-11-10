package com.orange.corepayments.model;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "payments")
@Entity(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    @Column
    private String reason;

    private BigDecimal reward;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id")
    private PaymentStatus paymentStatus;

    LocalDateTime updatedDate;

    LocalDateTime createdDate;

    private UUID requestId;


    public Optional<String> getReason() {
        return Optional.ofNullable(reason);
    }
}



