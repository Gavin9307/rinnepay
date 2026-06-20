package com.gavin.rinnepay.operations.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "settlement_payment")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SettlementPayment {
    @EmbeddedId
    private SettlementPaymentId settlementPaymentId;

    @MapsId("settlementId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "settlement_id")
    private Settlement settlement;
}
