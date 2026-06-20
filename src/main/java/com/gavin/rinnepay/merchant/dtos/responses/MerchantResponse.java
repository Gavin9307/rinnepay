package com.gavin.rinnepay.merchant.dtos.responses;

import com.gavin.rinnepay.common.enums.BusinessType;
import com.gavin.rinnepay.common.enums.MerchantStatus;

import java.util.UUID;

public record MerchantResponse (
        UUID id,
        String name,
        String email,
        String businessName,
        BusinessType businessType,
        String contactNumber,
        MerchantStatus merchantStatus
) { }