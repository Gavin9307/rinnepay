package com.gavin.rinnepay.merchant.dtos.requests;

import com.gavin.rinnepay.common.enums.BusinessType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MerchantSignupRequest(
        @NotBlank(message = "Name is required")
        @Size(max = 50, message = "Name should not be more than 50 characters long")
        String name,

        @Email
        @NotBlank(message = "Email is required")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password should be at least 8 characters long")
        String password,

        @Size(max = 50, message = "Business Name should not be more than 50 characters long")
        String businessName,

        BusinessType businessType
) { }