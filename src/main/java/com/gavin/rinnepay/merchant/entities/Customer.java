package com.gavin.rinnepay.merchant.entities;

import com.gavin.rinnepay.common.entities.BaseEntity;
import com.gavin.rinnepay.common.enums.MerchantStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "customer",
        indexes = {
                @Index(name = "idx_customer_merchant", columnList = "merchant_id"),
                @Index(name = "idx_customer_email", columnList = "email")
        }
)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 200)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(length = 20)
    private String contactNumber;

    private LocalDateTime deletedAt;

    @Column(nullable = false, length = 200)
    @Builder.Default
    private MerchantStatus status = MerchantStatus.PENDING_KYC;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "merchant_id", nullable = false)
    private Merchant merchant;
}
