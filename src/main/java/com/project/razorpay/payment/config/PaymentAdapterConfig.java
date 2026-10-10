package com.project.razorpay.payment.config;


import com.project.razorpay.common.enums.PaymentMethod;
import com.project.razorpay.payment.gateway.PaymentAdapter;
import com.project.razorpay.payment.gateway.adapters.CardPaymentAdapter;
import com.project.razorpay.payment.gateway.adapters.NetBankingPaymentAdapter;
import com.project.razorpay.payment.gateway.adapters.UpiPaymentAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class PaymentAdapterConfig {

    @Bean
    public Map<PaymentMethod, PaymentAdapter> paymentAdapterMap(){
        return Map.of(
                PaymentMethod.CARD, new CardPaymentAdapter(),
                PaymentMethod.NETBANKING, new NetBankingPaymentAdapter(),
                PaymentMethod.UPI, new UpiPaymentAdapter()

        );
    }

}
