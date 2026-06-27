package com.gavin.rinnepay.payment.services.Impl;

import com.gavin.rinnepay.common.enums.OrderStatus;
import com.gavin.rinnepay.common.exception.BusinessRuleViolationException;
import com.gavin.rinnepay.common.exception.DuplicateResourceException;
import com.gavin.rinnepay.common.exception.ResourceNotFoundException;
import com.gavin.rinnepay.payment.dtos.requests.OrderCreateRequest;
import com.gavin.rinnepay.payment.dtos.responses.OrderResponse;
import com.gavin.rinnepay.payment.dtos.responses.PaymentResponse;
import com.gavin.rinnepay.payment.entities.OrderRecord;
import com.gavin.rinnepay.payment.entities.Payment;
import com.gavin.rinnepay.payment.mappers.OrderMapper;
import com.gavin.rinnepay.payment.mappers.PaymentMapper;
import com.gavin.rinnepay.payment.repositories.OrderRepository;
import com.gavin.rinnepay.payment.repositories.PaymentRepository;
import com.gavin.rinnepay.payment.services.OrderService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final OrderMapper orderMapper;

    @Value("${payment.order.default-order-expiry-minutes:30}")
    private int defaultOrderExpiryMinutes;

    @Override
    @Transactional
    public OrderResponse create(UUID merchantId, OrderCreateRequest request){
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

        return orderMapper.toResponse(orderRecord);
    }

    @Override
    @Transactional
    public OrderResponse cancel(UUID merchantId, UUID orderId) {
        OrderRecord order = orderRepository.findByIdAndMerchantId(orderId, merchantId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", orderId));
        if (order.getOrderStatus() == OrderStatus.PAID || order.getOrderStatus() == OrderStatus.CANCELED) {
            throw new BusinessRuleViolationException("Cannot Cancel Order with status: " + order.getOrderStatus().name(), "ORDER_CANNOT_CANCEL");
        }
        order.setOrderStatus(OrderStatus.CANCELED);
        return orderMapper.toResponse(order);
    }
    @Override
    public List<PaymentResponse> listPayments(UUID merchantId, UUID orderId) {
        OrderRecord order = orderRepository.findByIdAndMerchantId(orderId,merchantId)
                .orElseThrow(()-> new ResourceNotFoundException("Order",orderId));

        List<Payment> payments = paymentRepository.findByOrder_Id(orderId);

        return paymentMapper.toResponseList(payments);
    }

    @Override
    public OrderResponse getById(UUID merchantId, UUID orderId) {
        OrderRecord order = orderRepository.findByIdAndMerchantId(orderId,merchantId)
                .orElseThrow(()-> new ResourceNotFoundException("Order",orderId));

        return orderMapper.toResponse(order);
    }
}
