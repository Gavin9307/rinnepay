package com.gavin.rinnepay.payment.entities;

import com.gavin.rinnepay.common.entities.BaseEntity;
import com.gavin.rinnepay.common.entities.Money;
import com.gavin.rinnepay.common.enums.OrderStatus;
import com.gavin.rinnepay.common.enums.PaymentMethod;
import com.gavin.rinnepay.common.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(
        name = "payment",
        indexes = {
                @Index(name = "idx_payment_order_id", columnList = "order_id"),
                @Index(name = "idx_payment_merchant_id", columnList = "merchant_id")
        }
)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Embedded
    private Money amount;

    @Column(nullable = false)
    private String idempotencyKey;

    @Column(length = 200)
    private String bankReference;

    @Column(length = 100)
    private String errorCode;

    @Column(length = 255)
    private String errorDescription;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String,Object> methodDetails = new HashMap<>();

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus =  PaymentStatus.CREATED;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private LocalDateTime authorizedAt;
    private LocalDateTime capturedAt;
    private LocalDateTime failedAt;
    private LocalDateTime refundedAt;
    private LocalDateTime settledAt;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private OrderRecord order;

    @Column(name = "merchant_id", nullable = false)
    private UUID merchantId;

}
