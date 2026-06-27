package com.gavin.rinnepay.merchant.mappers;

import com.gavin.rinnepay.merchant.dtos.requests.MerchantSignupRequest;
import com.gavin.rinnepay.merchant.dtos.responses.MerchantResponse;
import com.gavin.rinnepay.merchant.entities.Merchant;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MerchantMapper {
    Merchant toEntityFromSignupRequest(MerchantSignupRequest request);
    MerchantResponse toResponse(Merchant entity);
}
