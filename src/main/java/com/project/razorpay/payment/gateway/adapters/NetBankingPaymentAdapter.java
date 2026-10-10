package com.project.razorpay.payment.gateway.adapters;

import com.project.razorpay.payment.gateway.PaymentAdapter;
import com.project.razorpay.payment.gateway.dto.PaymentRequest;
import com.project.razorpay.payment.gateway.dto.PaymentResult;

public class NetBankingPaymentAdapter implements PaymentAdapter {

    @Override
    public PaymentResult initiate(PaymentRequest request){
        return null;
    }

}
