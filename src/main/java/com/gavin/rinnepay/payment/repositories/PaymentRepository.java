package com.gavin.rinnepay.payment.repositories;

import com.gavin.rinnepay.payment.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
