package com.gavin.rinnepay.payment.services.Impl;

import com.gavin.rinnepay.common.enums.OrderStatus;
import com.gavin.rinnepay.common.enums.PaymentStatus;
import com.gavin.rinnepay.common.exception.BusinessRuleViolationException;
import com.gavin.rinnepay.common.exception.ResourceNotFoundException;
import com.gavin.rinnepay.payment.dtos.requests.PaymentInitRequest;
import com.gavin.rinnepay.payment.dtos.responses.PaymentResponse;
import com.gavin.rinnepay.payment.entities.OrderRecord;
import com.gavin.rinnepay.payment.entities.Payment;
import com.gavin.rinnepay.payment.gateways.PaymentGatewayRouter;
import com.gavin.rinnepay.payment.gateways.dtos.PaymentAdapterRequest;
import com.gavin.rinnepay.payment.gateways.dtos.PaymentAdapterResponse;
import com.gavin.rinnepay.payment.mappers.PaymentMapper;
import com.gavin.rinnepay.payment.repositories.OrderRepository;
import com.gavin.rinnepay.payment.repositories.PaymentRepository;
import com.gavin.rinnepay.payment.services.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final PaymentGatewayRouter paymentGatewayRouter;
    private final PaymentMapper paymentMapper;

    @Override
    @Transactional
    public PaymentResponse initiate(UUID merchantId, PaymentInitRequest request){
        OrderRecord order = orderRepository.findByIdAndMerchantId(request.orderId(), merchantId).orElseThrow(() -> new ResourceNotFoundException("Order",request.orderId()));
        if(order.getOrderStatus() == OrderStatus.PAID || order.getOrderStatus() == OrderStatus.CANCELED){
            throw new BusinessRuleViolationException("ORDER_NOT_PAYABLE", "Order cannot accept payment in status : "+ order.getOrderStatus());
        }

        order.setOrderStatus(OrderStatus.ATTEMPTED);
        order.setAttempts(order.getAttempts() + 1);

        Payment payment = Payment.builder()
                .order(order)
                .merchantId(merchantId)
                .amount(order.getAmount())
                .status(PaymentStatus.CREATED)
                .method(request.method())
                .methodDetails(request.methodDetails())
        .build();

        paymentRepository.save(payment);

        PaymentAdapterRequest paymentRequest = new PaymentAdapterRequest(
                payment.getId(),
                request.orderId(),
                merchantId,
                payment.getAmount(),
                payment.getMethod(),
                payment.getMethodDetails()
        );

        PaymentAdapterResponse adapterResponse = paymentGatewayRouter.initiate(paymentRequest);

        switch (adapterResponse) {
            case PaymentAdapterResponse.Pending pending ->
                    payment.setProcessorReference(pending.registrationReference());
            case PaymentAdapterResponse.Failure failure -> {
                    payment.setStatus(PaymentStatus.FAILED);
                    payment.setErrorCode(failure.errorCode());
                    payment.setErrorDescription(failure.errorDescription());
            }
        }

        payment = paymentRepository.save(payment);
        orderRepository.save(order);

        return paymentMapper.toResponse(payment);
    }
}
