package com.petrius.payment_journal.service;


import com.petrius.payment_journal.domain.PaymentRequest;
import com.petrius.payment_journal.domain.PaymentResponse;
import com.petrius.payment_journal.entity.Payment;
import com.petrius.payment_journal.mapper.PaymentMapper;
import com.petrius.payment_journal.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public PaymentService(PaymentRepository paymentRepository, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }

    public PaymentResponse add(PaymentRequest paymentRequest){
        Payment payment = this.paymentMapper.toPayment(paymentRequest);
        payment.setDateTime(LocalDateTime.now());
        Payment addedPayment = this.paymentRepository.add(payment);
        return this.paymentMapper.toPaymentResponse(addedPayment);
    }


    public List<PaymentResponse> getPaymentList() {
        List<Payment> paymentList = this.paymentRepository.getAll();
        List<PaymentResponse> paymentResponseList = paymentList.stream()
                .map(payment -> this.paymentMapper.toPaymentResponse(payment))
                .toList();

        return paymentResponseList;
    }
}
