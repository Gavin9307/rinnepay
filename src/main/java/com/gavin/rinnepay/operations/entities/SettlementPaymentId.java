package com.gavin.rinnepay.operations.entities;

import com.gavin.rinnepay.common.entities.BaseEntity;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class SettlementPaymentId {
    private UUID settlementId;
    private UUID paymentId;
}
