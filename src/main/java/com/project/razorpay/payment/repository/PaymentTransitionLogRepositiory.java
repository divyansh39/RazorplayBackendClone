package com.project.razorpay.payment.repository;

import com.project.razorpay.payment.entity.PaymentTransitionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaymentTransitionLogRepositiory extends JpaRepository<PaymentTransitionLog, UUID> {
}
