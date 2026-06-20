package com.gavin.rinnepay.payment.dtos.requests;

import com.gavin.rinnepay.common.entities.Money;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Map;

public record OrderCreateRequest(

        @NotNull(message = "Amount is required")
        Money amount,

        Map<String,Object> notes,

        String receipt,

        LocalDateTime expiresAt
) { }