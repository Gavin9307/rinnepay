package com.gavin.rinnepay.payment.dtos.requests;

import com.gavin.rinnepay.common.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;

import java.util.Map;
import java.util.UUID;

public record PaymentInitRequest(
        @NotNull(message = "Order Id is required")
        UUID orderId,
        @NotNull(message = "Order Id is required")
        PaymentMethod method,
        Map<String,Object> methodDetails
) { }
