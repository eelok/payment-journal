package com.petrius.payment_journal.controller;


import com.petrius.payment_journal.domain.PaymentRequest;
import com.petrius.payment_journal.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("api/v1/add-payment")
    public ResponseEntity<Void> addPayment(@Valid @RequestBody PaymentRequest paymentRequest){
        PaymentResponse paymentResponse = this.paymentService.add(paymentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentResponse);
    }
}
