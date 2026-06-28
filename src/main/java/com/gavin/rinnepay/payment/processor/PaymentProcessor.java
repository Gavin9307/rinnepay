package com.gavin.rinnepay.payment.processor;

import com.gavin.rinnepay.payment.processor.dtos.PaymentProcessorRequest;
import com.gavin.rinnepay.payment.processor.dtos.PaymentProcessorResponse;

public interface PaymentProcessor {
    PaymentProcessorResponse charge(PaymentProcessorRequest request);
}
