package com.project.razorpay.payment.processor.dto;

import com.project.razorpay.common.entity.Money;
import com.project.razorpay.common.enums.PaymentMethod;

import java.util.Map;
import java.util.UUID;

public record PaymentProcessorRequest(
        UUID processingId,
        UUID paymentId,
        PaymentMethod method,
        Money amount,
        String pan, //--not using these2 params , becasue it will only come in case of card related payemnt
        String expiry,// -- for upi or netbanking these fields will be null
        Map<String, Object> methodDetails
) {

    public static  PaymentProcessorRequest card(UUID paymentId, String pan, String expiry, Money amount, Map<String, Object> details){
        return new PaymentProcessorRequest(UUID.randomUUID(), paymentId, PaymentMethod.CARD, amount, pan, expiry, details);
    }

    public static  PaymentProcessorRequest nonCard(UUID paymentId, PaymentMethod method, Money amount, Map<String, Object> details){
        return new PaymentProcessorRequest(UUID.randomUUID(), paymentId, method, amount, null, null, details);
    }

}
