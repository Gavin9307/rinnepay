package com.gavin.rinnepay.merchant.services.Impl;

import com.gavin.rinnepay.common.exception.ResourceNotFoundException;
import com.gavin.rinnepay.common.util.RandomizerUtil;
import com.gavin.rinnepay.merchant.dtos.requests.ApiKeyCreateRequest;
import com.gavin.rinnepay.merchant.dtos.responses.ApiKeyCreateResponse;
import com.gavin.rinnepay.merchant.dtos.responses.ApiKeyResponse;
import com.gavin.rinnepay.merchant.entities.ApiKey;
import com.gavin.rinnepay.merchant.entities.Merchant;
import com.gavin.rinnepay.merchant.mappers.ApiKeyMapper;
import com.gavin.rinnepay.merchant.repositories.ApiKeyRepository;
import com.gavin.rinnepay.merchant.repositories.MerchantRepository;
import com.gavin.rinnepay.merchant.services.ApiKeyService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApiKeyServiceImpl implements ApiKeyService {

    private final ApiKeyRepository apiKeyRepository;
    private final MerchantRepository merchantRepository;
    private final ApiKeyMapper apiKeyMapper;

    @Override
    @Transactional
    public ApiKeyCreateResponse create(UUID merchantId, ApiKeyCreateRequest request) {
        Merchant merchant = merchantRepository.findById(merchantId).orElseThrow(() -> new ResourceNotFoundException("merchant", merchantId));
        String keyId = "rin_" + request.environment().name().toLowerCase() + "_" + RandomizerUtil.randomBase64(24);
        String rawSecret = RandomizerUtil.randomBase64(40); // output length will be 54

        ApiKey apiKey = ApiKey.builder()
                .merchant(merchant)
                .keyId(keyId)
                .keySecretHash(rawSecret) // TODO : Encode with BcryptPasswordEncoder
                .environment(request.environment())
                .build();

        apiKey = apiKeyRepository.save(apiKey);

        return new ApiKeyCreateResponse(apiKey.getId(), keyId, rawSecret, request.environment().name());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ApiKeyResponse> listByMerchant(UUID merchantId){
        if (!merchantRepository.existsById(merchantId)) {
            throw new ResourceNotFoundException("merchant", merchantId);
        }
        List<ApiKey> apiKeys = apiKeyRepository.findByMerchant_Id(merchantId);
        return apiKeyMapper.toResponseList(apiKeys);
    }

    @Override
    @Transactional
    public void revoke(UUID merchantId, UUID apiKeyId){
        ApiKey apiKey = apiKeyRepository.findById(apiKeyId).filter(key -> key.getMerchant().getId().equals(merchantId)).orElseThrow(() -> new ResourceNotFoundException("apiKey", apiKeyId));
        apiKey.setEnabled(false);
    }

    @Override
    @Transactional
    public ApiKeyCreateResponse rotate(UUID merchantId, UUID apiKeyId){
        ApiKey apiKey = apiKeyRepository.findById(apiKeyId).filter(key -> key.getMerchant().getId().equals(merchantId)).orElseThrow(() -> new ResourceNotFoundException("apiKey", apiKeyId));

        if (!apiKey.getEnabled()) throw new RuntimeException("Cannot rotate a revoked api key.");

        String rawSecret = RandomizerUtil.randomBase64(40); // output length will be 54
        apiKey.setPreviousKeySecretHash(apiKey.getKeySecretHash());
        apiKey.setKeySecretHash(rawSecret); // TODO : Encode with BcryptPasswordEncoder
        apiKey.setGracePeriodExpiresAt(LocalDateTime.now().plusHours(24));
        apiKey.setLastRotatedAt(LocalDateTime.now());
        return new ApiKeyCreateResponse(apiKey.getId(), apiKeyId.toString(), rawSecret, apiKey.getEnvironment().name());
    }
}
