package com.gavin.rinnepay.payment.processor;

import com.gavin.rinnepay.common.enums.PaymentMethod;
import com.gavin.rinnepay.payment.processor.dtos.PaymentProcessorRequest;
import com.gavin.rinnepay.payment.processor.dtos.PaymentProcessorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class PaymentProcessorRouter {
    private final Map<PaymentMethod, PaymentProcessor> paymentProcessorRequestMap;

    public PaymentProcessorResponse charge(PaymentProcessorRequest request) {
        PaymentProcessor processor = paymentProcessorRequestMap.get(request.method());
        if (processor == null) {
            throw new IllegalArgumentException("No payment processor found for method " + request.method());
        }

        return processor.charge(request);
    }
}
