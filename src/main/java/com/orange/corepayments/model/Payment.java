package com.orange.corepayments.model;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
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
    private UUID id;

    private BigDecimal amount;

    @Column
    private String reason;

    private BigDecimal reward;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id")
    private PaymentStatus paymentStatus;


    public Optional<String> getReason() {
        return Optional.ofNullable(reason);
    }
}



