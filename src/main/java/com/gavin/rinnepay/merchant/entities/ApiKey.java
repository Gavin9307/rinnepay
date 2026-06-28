package com.gavin.rinnepay.merchant.entities;

import com.gavin.rinnepay.common.entities.BaseEntity;
import com.gavin.rinnepay.common.enums.Environment;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "api_key",
        indexes = {
                @Index(name = "idx_api_key_merchant_env", columnList = "merchant_id, environment, enabled")
        }
)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiKey extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true, length = 200)
    private String keyId;

    @Column(nullable = false, length = 200)
    private String keySecretHash;

    @Column(length = 200)
    private String previousKeySecretHash;

    @Enumerated(EnumType.STRING)
    private Environment environment;

    @Column(nullable = false)
    @Builder.Default
    private Boolean enabled = true;

    private LocalDateTime lastUsedAt;
    private LocalDateTime lastRotatedAt;
    private LocalDateTime gracePeriodExpiresAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "merchant_id",nullable = false)
    private Merchant merchant;
}
