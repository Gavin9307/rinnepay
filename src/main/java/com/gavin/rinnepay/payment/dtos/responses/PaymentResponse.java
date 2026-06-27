package com.gavin.rinnepay.payment.dtos.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gavin.rinnepay.common.entities.Money;
import com.gavin.rinnepay.common.enums.PaymentMethod;
import com.gavin.rinnepay.common.enums.PaymentStatus;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PaymentResponse (
        UUID id,
        UUID orderId,
        UUID merchantId,
        Money amount,
        PaymentStatus status,
        PaymentMethod method,
        Map<String, Object> methodDetails,
        String errorCode,
        String errorDescription,
        LocalDateTime capturedAt,
        LocalDateTime createdAt
) { }
