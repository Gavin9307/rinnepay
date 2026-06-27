package com.gavin.rinnepay.merchant.mappers;
import com.gavin.rinnepay.merchant.dtos.responses.ApiKeyCreateResponse;
import com.gavin.rinnepay.merchant.dtos.responses.ApiKeyResponse;
import com.gavin.rinnepay.merchant.entities.ApiKey;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ApiKeyMapper {
    ApiKeyResponse toResponse(ApiKey apiKey);
    ApiKeyCreateResponse toCreateResponse(ApiKey apiKey);
    List<ApiKeyResponse> toResponseList(List<ApiKey> apiKeys);
}
