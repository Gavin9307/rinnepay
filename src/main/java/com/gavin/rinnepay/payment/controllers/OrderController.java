package com.gavin.rinnepay.payment.controllers;

import com.gavin.rinnepay.payment.dtos.requests.OrderCreateRequest;
import com.gavin.rinnepay.payment.dtos.responses.OrderCreateResponse;
import com.gavin.rinnepay.payment.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    UUID merchantId = UUID.fromString("98f2b699-84a6-489b-9246-38704a12bba3"); // TODO : Replace it with MerchantContext

    @PostMapping("/create")
    public ResponseEntity<OrderCreateResponse> create(@RequestBody @Valid OrderCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.create(merchantId, request));

    }
}
