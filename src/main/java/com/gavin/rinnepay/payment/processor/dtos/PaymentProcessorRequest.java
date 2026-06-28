package com.gavin.rinnepay.payment.processor.dtos;

import com.gavin.rinnepay.common.entities.Money;
import com.gavin.rinnepay.common.enums.PaymentMethod;

import java.util.Map;

public record PaymentProcessorRequest (
    PaymentMethod method,
    Money amount,
    Map<String, Object> methodDetails
) { }