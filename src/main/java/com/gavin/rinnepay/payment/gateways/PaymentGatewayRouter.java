package com.gavin.rinnepay.payment.gateways;

import com.gavin.rinnepay.common.enums.PaymentMethod;
import com.gavin.rinnepay.payment.gateways.dtos.PaymentAdapterRequest;
import com.gavin.rinnepay.payment.gateways.dtos.PaymentAdapterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class PaymentGatewayRouter {

    private final Map<PaymentMethod,PaymentAdapter> paymentAdapterMap;

    public PaymentAdapterResponse initiate(PaymentAdapterRequest request){
        PaymentAdapter adapter = paymentAdapterMap.get(request.method());
        if(adapter == null){
            throw new IllegalArgumentException("No payment adapter found for method " + request.method());
        }
        return adapter.initiate(request);
    }
}
