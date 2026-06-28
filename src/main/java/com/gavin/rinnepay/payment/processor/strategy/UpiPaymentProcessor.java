package com.gavin.rinnepay.payment.processor.strategy;

import com.gavin.rinnepay.payment.processor.PaymentProcessor;
import com.gavin.rinnepay.payment.processor.dtos.PaymentProcessorRequest;
import com.gavin.rinnepay.payment.processor.dtos.PaymentProcessorResponse;

public class UpiPaymentProcessor  implements PaymentProcessor {

    @Override
    public PaymentProcessorResponse charge(PaymentProcessorRequest request) {
        return null;
    }

}