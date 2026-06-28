package com.gavin.rinnepay.payment.configs;

import com.gavin.rinnepay.common.enums.PaymentMethod;
import com.gavin.rinnepay.payment.processor.PaymentProcessor;
import com.gavin.rinnepay.payment.processor.strategy.CardPaymentProcessor;
import com.gavin.rinnepay.payment.processor.strategy.NetBankingPaymentProcessor;
import com.gavin.rinnepay.payment.processor.strategy.UpiPaymentProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Map;

@Configuration
public class PaymentProcessorConfig {
    @Bean
    public Map<PaymentMethod, PaymentProcessor> paymentProcessorRequestMap() {
        return Map.of(
                PaymentMethod.UPI,new UpiPaymentProcessor(),
                PaymentMethod.NETBANKING,new NetBankingPaymentProcessor(),
                PaymentMethod.CARD,new CardPaymentProcessor()
        );
    }
}
