package com.gavin.rinnepay.merchant.services;

import com.gavin.rinnepay.merchant.dtos.requests.ApiKeyCreateRequest;
import com.gavin.rinnepay.merchant.dtos.responses.ApiKeyCreateResponse;
import com.gavin.rinnepay.merchant.dtos.responses.ApiKeyResponse;

import java.util.List;
import java.util.UUID;

public interface ApiKeyService {
    ApiKeyCreateResponse create(UUID merchantId, ApiKeyCreateRequest request);
    List<ApiKeyResponse> listByMerchant(UUID merchantId);
    void revoke(UUID merchantId,UUID apiKeyId);
    ApiKeyCreateResponse rotate(UUID merchantId, UUID apiKeyId);
}
