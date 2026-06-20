package com.gavin.rinnepay.payment.services.Impl;

import com.gavin.rinnepay.common.exception.DuplicateResourceException;
import com.gavin.rinnepay.payment.dtos.requests.OrderCreateRequest;
import com.gavin.rinnepay.payment.dtos.responses.OrderCreateResponse;
import com.gavin.rinnepay.payment.entities.OrderRecord;
import com.gavin.rinnepay.payment.repositories.OrderRepository;
import com.gavin.rinnepay.payment.services.OrderService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Value("${payment.order.default-order-expiry-minutes:30}")
    private int defaultOrderExpiryMinutes;

    @Override
    @Transactional
    public OrderCreateResponse create(UUID merchantId, OrderCreateRequest request){
        if(request.receipt() != null && orderRepository.existsByReceiptAndMerchantId(request.receipt(), merchantId)){
            throw new DuplicateResourceException("Order with receipt " + request.receipt() + " already exists.", "ORDER_RECEIPT_DUPLICATE");
        }
        OrderRecord orderRecord = new OrderRecord().builder()
                .amount(request.amount())
                .merchantId(merchantId)
                .receipt(request.receipt())
                .notes(request.notes())
                .expiresAt(request.expiresAt() != null ? request.expiresAt() : LocalDateTime.now().plusMinutes(defaultOrderExpiryMinutes))
                .build();
        orderRecord = orderRepository.save(orderRecord);

        return new OrderCreateResponse(orderRecord.getId(),orderRecord.getMerchantId(),orderRecord.getReceipt(),orderRecord.getAmount(),orderRecord.getOrderStatus(),orderRecord.getAttempts(),orderRecord.getNotes(),orderRecord.getExpiresAt(),null);
    }
}
