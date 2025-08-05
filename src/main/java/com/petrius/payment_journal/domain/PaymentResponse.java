package com.petrius.payment_journal.domain;

import com.petrius.payment_journal.entity.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PaymentResponse {

    private Long id;
    private BigDecimal amount;
    private LocalDateTime dateTime;
    private String description;
    private PaymentType paymentType;
}
