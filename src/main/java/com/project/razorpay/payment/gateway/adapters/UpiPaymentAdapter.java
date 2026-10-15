package com.project.razorpay.payment.gateway.adapters;

import com.project.razorpay.common.enums.PaymentMethod;
import com.project.razorpay.payment.gateway.PaymentAdapter;
import com.project.razorpay.payment.gateway.dto.PaymentRequest;
import com.project.razorpay.payment.gateway.dto.PaymentResult;
import com.project.razorpay.payment.processor.PaymentProcessorRouter;
import com.project.razorpay.payment.processor.dto.PaymentProcessorRequest;
import com.project.razorpay.payment.processor.dto.PaymentProcessorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class UpiPaymentAdapter implements PaymentAdapter {

    private final PaymentProcessorRouter paymentProcessorRouter;

    @Override
    public PaymentResult initiate(PaymentRequest request){

        log.info("Initiate payment with Upi PaymentAdapters with paymentID: {}",request.paymentId());

        try {
            PaymentProcessorRequest paymentProcessorRequest = PaymentProcessorRequest.nonCard(
                    request.paymentId(),
                    PaymentMethod.UPI,
                    request.amount(),
                    request.methodDetails()
            );

            PaymentProcessorResponse paymentProcessorResponse =
                    paymentProcessorRouter.charge(paymentProcessorRequest);


            if (paymentProcessorResponse instanceof PaymentProcessorResponse.Failure) {
                PaymentProcessorResponse.Failure failure =
                        (PaymentProcessorResponse.Failure) paymentProcessorResponse;

                return new PaymentResult.Failure(
                        failure.errorcode(),
                        failure.errorDiscription()
                );

            } else if (paymentProcessorResponse instanceof PaymentProcessorResponse.Pending) {
                PaymentProcessorResponse.Pending pending =
                        (PaymentProcessorResponse.Pending) paymentProcessorResponse;

                return new PaymentResult.Pending(
                        pending.processorReference()
                );

            } else if (paymentProcessorResponse instanceof PaymentProcessorResponse.Success) {
                PaymentProcessorResponse.Success success =
                        (PaymentProcessorResponse.Success) paymentProcessorResponse;

                return new PaymentResult.Success(
                        success.bankReference()
                );
            }

            throw new IllegalStateException(
                    "Unsupported payment processor response: " + paymentProcessorResponse
            );

        } catch (Exception e) {
            log.warn("UPI failed, paymentId: {}",request.paymentId());
            return new PaymentResult.Failure("UPI_FAILED", e.getMessage());
        }

    }

    @Override
    public PaymentResult capture(UUID paymentId) {
        return new PaymentResult.Success("UPI_REF");
    }

}
