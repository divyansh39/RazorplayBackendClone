package com.project.razorpay.payment.processor.dto;

import com.project.razorpay.common.entity.Money;
import com.project.razorpay.common.enums.PaymentMethod;

import java.util.Map;

public record PaymentProcessorRequest(
        PaymentMethod method,
        Money amount,
//        String pan, --not using these2 params , becasue it will only come in case of card related payemnt
//        String expiry, -- for upi or netbanking these fields will be null
        Map<String, Object> methodDetails
) {
}
