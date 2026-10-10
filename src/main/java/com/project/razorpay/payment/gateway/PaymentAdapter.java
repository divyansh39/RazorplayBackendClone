package com.project.razorpay.payment.gateway;

import com.project.razorpay.payment.gateway.dto.PaymentRequest;
import com.project.razorpay.payment.gateway.dto.PaymentResult;

public interface PaymentAdapter {

    public PaymentResult initiate(PaymentRequest request);

}
