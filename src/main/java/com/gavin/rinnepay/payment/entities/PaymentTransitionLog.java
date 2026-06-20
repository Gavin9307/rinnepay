package com.gavin.rinnepay.payment.entities;

import com.gavin.rinnepay.common.enums.PaymentActor;
import com.gavin.rinnepay.common.enums.PaymentEvent;
import com.gavin.rinnepay.common.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payment_transition_log")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentTransitionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    @Enumerated(EnumType.STRING)
    private PaymentStatus fromStatus;

    @Enumerated(EnumType.STRING)
    private PaymentStatus toStatus;

    @Enumerated(EnumType.STRING)
    private PaymentEvent event;

    @Enumerated(EnumType.STRING)
    private PaymentActor actor;

    @Column(nullable = false)
    private LocalDateTime occurredAt;
}
