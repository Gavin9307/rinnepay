package com.gavin.rinnepay.payment.gateways;

import com.gavin.rinnepay.payment.gateways.dtos.PaymentAdapterRequest;
import com.gavin.rinnepay.payment.gateways.dtos.PaymentAdapterResponse;

public interface PaymentAdapter {
    PaymentAdapterResponse initiate(PaymentAdapterRequest request);
}
