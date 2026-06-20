package com.gavin.rinnepay.merchant.dtos.responses;

import com.gavin.rinnepay.common.enums.Environment;

import java.time.LocalDateTime;
import java.util.UUID;

public record ApiKeyResponse(
        UUID id,
        String keyId,
        Environment environment,
        Boolean enabled,
        LocalDateTime lastUsedAt,
        LocalDateTime lastRotatedAt,
        LocalDateTime gracePeriodExpiresAt,
        LocalDateTime createdAt
) { }
