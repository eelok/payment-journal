package com.petrius.payment_journal.controller;


import com.petrius.payment_journal.domain.MonthlySummary;
import com.petrius.payment_journal.domain.PaymentRequest;
import com.petrius.payment_journal.domain.PaymentResponse;
import com.petrius.payment_journal.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("api/v1/payments")
    public ResponseEntity<PaymentResponse> addPayment(@Valid @RequestBody PaymentRequest paymentRequest){
        PaymentResponse paymentResponse = this.paymentService.add(paymentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentResponse);
    }

    @GetMapping("api/v1/payments")
    public ResponseEntity<List<PaymentResponse>> getPaymentList(){
        List<PaymentResponse> paymentList = this.paymentService.getPaymentList();
        return ResponseEntity.status(HttpStatus.OK).body(paymentList);
    }

    @GetMapping("api/v1/payments/monthly-summary")
    public ResponseEntity<List<MonthlySummary>> getPaymentMonthlySummary(){
        List<MonthlySummary> monthlyPaymentSummary = this.paymentService.getMonthlyPaymentSummary();
        return ResponseEntity.status(HttpStatus.OK).body(monthlyPaymentSummary);
    }
}
