package com.gavin.rinnepay.merchant.controllers;

import com.gavin.rinnepay.merchant.dtos.requests.ApiKeyCreateRequest;
import com.gavin.rinnepay.merchant.dtos.responses.ApiKeyResponse;
import com.gavin.rinnepay.merchant.dtos.responses.ApiKeyCreateResponse;
import com.gavin.rinnepay.merchant.services.ApiKeyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/merchant/{merchantId}/api-keys")
@RequiredArgsConstructor
public class ApiKeyController {
    private final ApiKeyService apiKeyService;

    @PostMapping("/create")
    public ResponseEntity<ApiKeyCreateResponse> create(@PathVariable UUID merchantId, @RequestBody ApiKeyCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(apiKeyService.create(merchantId, request));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ApiKeyResponse>> listByMerchant(@PathVariable UUID merchantId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(apiKeyService.listByMerchant(merchantId));
    }

    @DeleteMapping("/{apiKeyId}/revoke")
    public ResponseEntity<Void> revoke(@PathVariable UUID merchantId, @PathVariable UUID apiKeyId) {
        apiKeyService.revoke(merchantId,apiKeyId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{apiKeyId}/rotate")
    public ResponseEntity<ApiKeyCreateResponse> rotate(@PathVariable UUID merchantId, @PathVariable UUID apiKeyId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(apiKeyService.rotate(merchantId, apiKeyId));
    }
}
