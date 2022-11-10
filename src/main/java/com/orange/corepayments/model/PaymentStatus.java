package com.orange.corepayments.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.util.UUID;

import static javax.persistence.EnumType.STRING;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "payments_status")
public class PaymentStatus {

    public enum Id {
        UNPROCESSED,
        PENDING_AUTHORIZATION,
        PENDING_CONFIRMATION,
        SUCCEEDED,
        FAILED;
    }


    @javax.persistence.Id
    private Long id;

    @Enumerated(STRING)
    private Id name;
}
