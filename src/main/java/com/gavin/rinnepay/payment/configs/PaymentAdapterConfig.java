package com.gavin.rinnepay.payment.configs;

import com.gavin.rinnepay.common.enums.PaymentMethod;
import com.gavin.rinnepay.payment.gateways.PaymentAdapter;
import com.gavin.rinnepay.payment.gateways.adapters.CardPaymentAdapter;
import com.gavin.rinnepay.payment.gateways.adapters.NetBankingAdapter;
import com.gavin.rinnepay.payment.gateways.adapters.UpiPaymentAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class PaymentAdapterConfig {
    @Bean
    public Map<PaymentMethod, PaymentAdapter> paymentAdapterMap() {
        return Map.of(
                PaymentMethod.CARD, new CardPaymentAdapter(),
                PaymentMethod.NETBANKING,new NetBankingAdapter(),
                PaymentMethod.UPI, new UpiPaymentAdapter()
        );
    }
}
