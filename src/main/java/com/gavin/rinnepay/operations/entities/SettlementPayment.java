package com.gavin.rinnepay.operations.entities;

import com.gavin.rinnepay.common.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "settlement_payment")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SettlementPayment extends BaseEntity {
    @EmbeddedId
    private SettlementPaymentId settlementPaymentId;

    @MapsId("settlementId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "settlement_id")
    private Settlement settlement;
}
