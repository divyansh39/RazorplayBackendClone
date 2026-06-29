package com.project.razorpay.merchant.service;

import com.project.razorpay.merchant.dto.request.MerchantSignUpRequest;
import com.project.razorpay.merchant.dto.response.MerchantResponse;
import jakarta.validation.Valid;

public interface AuthService {

    MerchantResponse signUp(@Valid MerchantSignUpRequest request);
}
