package com.gavin.rinnepay.operations.entities;

import com.gavin.rinnepay.common.entities.BaseEntity;
import com.gavin.rinnepay.common.entities.Money;
import com.gavin.rinnepay.common.enums.SettlementStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "settlement")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Settlement extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID merchantId;

    @Enumerated(EnumType.STRING)
    private SettlementStatus status;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name= "currency", column = @Column(name = "gross_currency", nullable = false)),
            @AttributeOverride(name = "amountUnits", column = @Column(name = "gross_amount_units", nullable = false))
    })
    private Money grossAmount;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name= "currency", column = @Column(name = "refund_currency")),
            @AttributeOverride(name = "amountUnits", column = @Column(name = "refund_amount_units"))
    })
    private Money refundAmount;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name= "currency", column = @Column(name = "net_currency")),
            @AttributeOverride(name = "amountUnits", column = @Column(name = "net_amount_units"))
    })
    private Money netAmount;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name= "currency", column = @Column(name = "fee_currency")),
            @AttributeOverride(name = "amountUnits", column = @Column(name = "fee_amount_units"))
    })
    private Money feeAmount;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name= "currency", column = @Column(name = "gst_currency")),
            @AttributeOverride(name = "amountUnits", column = @Column(name = "gst_amount_units"))
    })
    private Money gstAmount;

    private String bankReference;

    private LocalDateTime processedAt;
}
