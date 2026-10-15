package com.project.razorpay.payment.config;


import com.project.razorpay.common.enums.PaymentMethod;
import com.project.razorpay.payment.gateway.PaymentAdapter;
import com.project.razorpay.payment.gateway.adapters.CardPaymentAdapter;
import com.project.razorpay.payment.gateway.adapters.NetBankingPaymentAdapter;
import com.project.razorpay.payment.gateway.adapters.UpiPaymentAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@RequiredArgsConstructor
@Configuration
public class PaymentAdapterConfig {

    private final NetBankingPaymentAdapter netBankingPaymentAdapter;
    private final CardPaymentAdapter cardPaymentAdapter;
    private final UpiPaymentAdapter upiPaymentAdapter;


    @Bean
    public Map<PaymentMethod, PaymentAdapter> paymentAdapterMap(){
        return Map.of(
                PaymentMethod.CARD, cardPaymentAdapter,
                PaymentMethod.NETBANKING, netBankingPaymentAdapter,
                PaymentMethod.UPI, upiPaymentAdapter

        );
    }

}
