package com.gavin.rinnepay.merchant.repositories;

import com.gavin.rinnepay.merchant.entities.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MerchantRepository extends JpaRepository<Merchant, UUID> {
    boolean existsByEmail(String email);
}
