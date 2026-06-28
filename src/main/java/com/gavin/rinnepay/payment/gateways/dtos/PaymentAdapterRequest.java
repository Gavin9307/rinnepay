package com.gavin.rinnepay.payment.gateways.dtos;

import com.gavin.rinnepay.common.entities.Money;
import com.gavin.rinnepay.common.enums.PaymentMethod;

import java.util.Map;
import java.util.UUID;

public record PaymentAdapterRequest (
    UUID paymentId,
    UUID OrderId,
    UUID merchantId,
    Money amount,
    PaymentMethod method,
    Map<String,Object> methodDetails
) { }