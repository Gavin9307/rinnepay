package com.gavin.rinnepay.payment.entities;

import com.gavin.rinnepay.common.entities.Money;
import com.gavin.rinnepay.common.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "order_record")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Embedded
    private Money amount;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private OrderStatus orderStatus =  OrderStatus.CREATED;

    @Column(nullable = false)
    @Builder.Default
    private Integer attempts = 0;

    @JdbcTypeCode((SqlTypes.JSON))
    @Column(columnDefinition = "jsonb")
    private Map<String,Object> notes;

    private String receipt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    //No FK - cross service boundary
    @Column(name = "merchant_id", nullable = false)
    private UUID merchantId;

}
