package com.gavin.rinnepay.payment.services;

import com.gavin.rinnepay.payment.dtos.requests.OrderCreateRequest;
import com.gavin.rinnepay.payment.dtos.responses.OrderCreateResponse;

import java.util.UUID;

public interface OrderService {
    OrderCreateResponse create(UUID merchantId, OrderCreateRequest orderCreateRequest);
}
