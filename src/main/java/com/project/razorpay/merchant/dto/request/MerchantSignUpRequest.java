package com.project.razorpay.merchant.dto.request;

import com.project.razorpay.common.enums.BusinessType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MerchantSignUpRequest(

        @NotNull(message = "name is required")
        @Size(max = 50, message = "name should not be more than 50 characters")
        String name,

        @Email
        @NotNull(message = "email is required")
        String email,

        @NotNull(message = "password is required")
        @Size(min = 8, message = "password must  be atleast 8 characters long")
        String password,

        @Size(max = 50, message = "business should not be more than 50 characters")
        String businessName,


        BusinessType businessType
) {
}
