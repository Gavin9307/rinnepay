package com.gavin.rinnepay.payment.repositories;

import com.gavin.rinnepay.payment.entities.OrderRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderRecord, UUID> {
    Boolean existsByReceiptAndMerchantId(String receipt, UUID merchantId);
    Optional<OrderRecord> findByIdAndMerchantId(UUID orderId, UUID merchantId);
}
