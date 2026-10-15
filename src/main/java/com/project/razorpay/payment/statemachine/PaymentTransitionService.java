package com.project.razorpay.payment.statemachine;

import com.project.razorpay.common.enums.PaymentActor;
import com.project.razorpay.common.enums.PaymentEvent;
import com.project.razorpay.common.enums.PaymentStatus;
import com.project.razorpay.payment.entity.Payment;
import com.project.razorpay.payment.entity.PaymentTransitionLog;
import com.project.razorpay.payment.repository.PaymentTransitionLogRepositiory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentTransitionService {

    private final PaymentTransitionLogRepositiory paymentTransitionLogRepositiory;
    private final PaymentStateMachine paymentStateMachine;

    public PaymentStatus apply(Payment payment, PaymentEvent event){
        PaymentStatus next= paymentStateMachine.transition(payment.getStatus(),event);
        payment.setStatus(next);
        PaymentTransitionLog log= PaymentTransitionLog.builder()
                .payment(payment)
                .fromStatus(payment.getStatus())
                .event(event)
                .toStatus(next)
                .actor(PaymentActor.SYSTEM)//to do fetch merchant context to identify actor
                .occuredAt(LocalDateTime.now())
                .build();

        paymentTransitionLogRepositiory.save(log);
        return next;
    }

}
