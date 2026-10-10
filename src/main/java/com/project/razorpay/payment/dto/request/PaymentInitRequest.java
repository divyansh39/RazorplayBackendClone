package com.project.razorpay.payment.dto.request;

import com.project.razorpay.common.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;

import java.util.Map;
import java.util.UUID;

public record PaymentInitRequest(

        @NotNull(message = "order Id is required")
        UUID orderId,

        @NotNull(message = "payment method is required")
        PaymentMethod method,

        Map<String, Object> methodDetails

) {
}
