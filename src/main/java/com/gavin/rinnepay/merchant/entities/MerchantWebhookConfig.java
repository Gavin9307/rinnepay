package com.gavin.rinnepay.merchant.entities;

import com.gavin.rinnepay.common.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(
        name = "merchant_webhook_config",
        indexes = {
                @Index(name = "idx_merchant_webhook_config_merchant_id", columnList = "merchant_id")
        }
)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MerchantWebhookConfig extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "merchant_id", nullable = false)
    private Merchant merchant;

    @Column(nullable = false, length = 500)
    private String targetUrl;

    @Column(length = 255)
    private String webhookSecretHash;

    @Column(nullable = false)
    @Builder.Default
    private Boolean enable = true;

    @Column(length = 255)
    private String eventTypes;
}
