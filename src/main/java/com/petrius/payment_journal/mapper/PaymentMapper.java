package com.petrius.payment_journal.mapper;

import com.petrius.payment_journal.domain.PaymentRequest;
import com.petrius.payment_journal.domain.PaymentResponse;
import com.petrius.payment_journal.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {


    public Payment toPayment(PaymentRequest paymentRequest){
        return Payment.builder()
                .amount(paymentRequest.getAmount())
                .paymentType(paymentRequest.getPaymentType())
                .description(paymentRequest.getDescription())
                .build();
    }

    public PaymentResponse toPaymentResponse(Payment payment) {
        return PaymentResponse.builder()
                .id(payment.getId())
                .amount(payment.getAmount())
                .dateTime(payment.getDateTime())
                .description(payment.getDescription())
                .paymentType(payment.getPaymentType())
                .build();
    }
}
