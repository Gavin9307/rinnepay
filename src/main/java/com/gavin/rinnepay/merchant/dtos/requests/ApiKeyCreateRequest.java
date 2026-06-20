package com.gavin.rinnepay.merchant.dtos.requests;

import com.gavin.rinnepay.common.enums.Environment;

public record ApiKeyCreateRequest(
        Environment environment
) { }
