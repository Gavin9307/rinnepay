package com.gavin.rinnepay.payment.processor.dtos;

public sealed interface PaymentProcessorResponse permits
        PaymentProcessorResponse.Pending,
        PaymentProcessorResponse.Success,
        PaymentProcessorResponse.Failure
{
    record Pending(String processorReference) implements PaymentProcessorResponse {}
    record Success(String processorReference, String bankReference) implements PaymentProcessorResponse {}
    record Failure(String processorReference, String errorCode, String errorDescription) implements PaymentProcessorResponse {}
}