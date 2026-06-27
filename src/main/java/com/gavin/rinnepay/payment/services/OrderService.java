package com.gavin.rinnepay.payment.services;

import com.gavin.rinnepay.payment.dtos.requests.OrderCreateRequest;
import com.gavin.rinnepay.payment.dtos.responses.OrderResponse;
import com.gavin.rinnepay.payment.dtos.responses.PaymentResponse;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    OrderResponse create(UUID merchantId, OrderCreateRequest orderCreateRequest);
    OrderResponse getById(UUID merchantId, UUID orderId);
    OrderResponse cancel(UUID merchantId, UUID orderId);
    List<PaymentResponse> listPayments(UUID merchantId, UUID orderId);
}
