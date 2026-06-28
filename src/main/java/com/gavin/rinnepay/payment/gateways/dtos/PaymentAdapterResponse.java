package com.gavin.rinnepay.payment.gateways.dtos;

public sealed interface PaymentAdapterResponse permits
        PaymentAdapterResponse.Pending,
        PaymentAdapterResponse.Failure
{
    record Pending(String registrationReference) implements PaymentAdapterResponse {}
    record Failure(String registrationReference, String errorCode, String errorDescription) implements PaymentAdapterResponse {}
}
