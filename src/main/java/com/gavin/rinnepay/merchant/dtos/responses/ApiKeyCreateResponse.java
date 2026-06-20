package com.gavin.rinnepay.merchant.dtos.responses;

import java.util.UUID;

public record ApiKeyCreateResponse(
    UUID id,
    String keyId,
    String keySecret,
    String environment
) { }
