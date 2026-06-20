package com.gavin.rinnepay.merchant.repositories;

import com.gavin.rinnepay.merchant.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AppUserRepository extends JpaRepository<AppUser, UUID> {
}
