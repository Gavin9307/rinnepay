package com.gavin.rinnepay.merchant.services;

import com.gavin.rinnepay.merchant.dtos.requests.MerchantSignupRequest;
import com.gavin.rinnepay.merchant.dtos.responses.MerchantResponse;

public interface AuthService {
    MerchantResponse signup(MerchantSignupRequest request);
}
