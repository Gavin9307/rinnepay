package com.gavin.rinnepay.merchant.entities;

import com.gavin.rinnepay.common.enums.BusinessType;
import com.gavin.rinnepay.common.enums.MerchantStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "merchant")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(length = 20)
    private String contactNumber;

    @Column(length = 200)
    private String businessName;

    @Column(length = 200)
    private String websiteUrl;

    @Column(length = 20)
    private String gstId;

    @Column(length = 20)
    private String panId;

    @Column(length = 200)
    private String settlementBankAccount;

    @Column(length = 20)
    private String settlementBankIfsc;

    @Column(length = 200)
    private String settlementBankAccountHolderName;

    @Enumerated(EnumType.STRING)
    private BusinessType businessType;

    @Column(nullable = false, length = 200)
    @Builder.Default
    private MerchantStatus status = MerchantStatus.PENDING_KYC;

}
