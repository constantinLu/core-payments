package com.orange.corepayments.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.Map;
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

    enum Id {
        NONE(1),
        PENDING_AUTHORIZATION(2),
        PENDING_CONFIRMATION(3),
        SUCCEEDED(4),
        FAILED(5);

        public final Integer id;

        private static Map<Integer, Id> map = new HashMap<>();

        Id(Integer id) {
            this.id = id;
        }

        static {
            for (Id value : Id.values()) {
                map.put(value.id, value);
            }
        }

        public static Id valueOf(int intId) {
            return map.get(intId);
        }
    }


    @javax.persistence.Id
    private UUID id;

    @Enumerated(STRING)
    private Id name;
}
