package com.gavin.rinnepay.merchant.services.Impl;

import com.gavin.rinnepay.common.enums.MerchantStatus;
import com.gavin.rinnepay.common.enums.UserRole;
import com.gavin.rinnepay.common.exception.DuplicateResourceException;
import com.gavin.rinnepay.merchant.dtos.requests.MerchantSignupRequest;
import com.gavin.rinnepay.merchant.dtos.responses.MerchantResponse;
import com.gavin.rinnepay.merchant.entities.AppUser;
import com.gavin.rinnepay.merchant.entities.Merchant;
import com.gavin.rinnepay.merchant.repositories.AppUserRepository;
import com.gavin.rinnepay.merchant.repositories.MerchantRepository;
import com.gavin.rinnepay.merchant.services.AuthService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AppUserRepository appUserRepository;
    private final MerchantRepository merchantRepository;

    @Override
    @Transactional
    public MerchantResponse signup(MerchantSignupRequest request){
        if(merchantRepository.existsByEmail(request.email())){
            throw new DuplicateResourceException("Merchant with email " + request.email() + " already exists.", "DUPLICATE_MERCHANT_EMAIL");
        }
        Merchant merchant = Merchant.builder()
                .businessName(request.businessName())
                .email(request.email())
                .name(request.name())
                .businessType(request.businessType())
                .status(MerchantStatus.PENDING_KYC)
                .build();

        merchant = merchantRepository.save(merchant);

        AppUser appUser = AppUser.builder()
                .email(request.email())
                .merchant(merchant)
                .passwordHash(request.password()) //TODO : Encrypt using Bcrypt
                .role(UserRole.OWNER)
                .build();

        appUserRepository.save(appUser);

        return new MerchantResponse(
                merchant.getId(),
                merchant.getName(),
                merchant.getEmail(),
                merchant.getBusinessName(),
                merchant.getBusinessType(),
                merchant.getContactNumber(),
                merchant.getStatus()
        );
    }
}
