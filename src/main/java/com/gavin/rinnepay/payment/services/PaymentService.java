package com.gavin.rinnepay.payment.services;

import com.gavin.rinnepay.payment.dtos.requests.PaymentInitRequest;
import com.gavin.rinnepay.payment.dtos.responses.PaymentResponse;

import java.util.UUID;

public interface PaymentService {
    PaymentResponse initiate(UUID merchantId, PaymentInitRequest request);
}
