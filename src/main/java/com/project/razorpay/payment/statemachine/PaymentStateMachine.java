package com.project.razorpay.payment.statemachine;

import com.project.razorpay.common.enums.PaymentEvent;
import com.project.razorpay.common.enums.PaymentStatus;
import com.project.razorpay.common.exception.InvalidStateTransitionException;

import java.util.Map;

public class PaymentStateMachine {

    //from- your current status
    //event- the event you are targeting
    private record Transition(PaymentStatus from, PaymentEvent event){

    }

    //here CREATED is from,
    // AUTHORISING is to and Authorize_attempt is the edge (like in a graph)
    private static final Map<Transition, PaymentStatus> TRANSITION= Map.ofEntries(
            Map.entry(new Transition(PaymentStatus.CREATED, PaymentEvent.AUTHORIZE_ATTEMPT),
                    PaymentStatus.AUTHORISING),
            Map.entry(new Transition(PaymentStatus.AUTHORISING, PaymentEvent.AUTHORIZE_SUCCESS),
                    PaymentStatus.AUTHORIZED),
            Map.entry(new Transition(PaymentStatus.AUTHORISING, PaymentEvent.AUTHORIZE_FAIL),
                    PaymentStatus.FAILED),
            Map.entry(new Transition(PaymentStatus.AUTHORIZED, PaymentEvent.CAPTURE_REQUEST),
                    PaymentStatus.CAPTURING),
            Map.entry(new Transition(PaymentStatus.CAPTURING, PaymentEvent.CAPTURE_SUCCESS),
                    PaymentStatus.CAPTURED),
            Map.entry(new Transition(PaymentStatus.CAPTURING, PaymentEvent.CAPTURE_FAIL),
                    PaymentStatus.AUTHORIZED),
            Map.entry(new Transition(PaymentStatus.CAPTURED, PaymentEvent.REFUND_INIT),
                    PaymentStatus.PARTIALLY_REFUNDED),
            Map.entry(new Transition(PaymentStatus.PARTIALLY_REFUNDED, PaymentEvent.REFUND_COMPLETE),
                    PaymentStatus.REFUNDED),
            Map.entry(new Transition(PaymentStatus.CAPTURED, PaymentEvent.REFUND_COMPLETE),
                    PaymentStatus.REFUNDED),
            Map.entry(new Transition(PaymentStatus.CAPTURED, PaymentEvent.SETTLE),
                    PaymentStatus.SETTLED),
            Map.entry(new Transition(PaymentStatus.SETTLED, PaymentEvent.REFUND_INIT),
                    PaymentStatus.PARTIALLY_REFUNDED),
            Map.entry(new Transition(PaymentStatus.CREATED, PaymentEvent.CANCEL),
                    PaymentStatus.CANCELLED),
            Map.entry(new Transition(PaymentStatus.AUTHORISING, PaymentEvent.CANCEL),
                    PaymentStatus.CANCELLED),
            Map.entry(new Transition(PaymentStatus.AUTHORIZED, PaymentEvent.CAPTURE_TIMEOUT),
                    PaymentStatus.AUTH_EXPIRED)
    );

    public PaymentStatus transition(PaymentStatus current, PaymentEvent event){
        PaymentStatus next= TRANSITION.get(new Transition(current, event));
        if(next==null){
            throw new InvalidStateTransitionException(current.name(), event.name());
        }
        return next;
    }

}
