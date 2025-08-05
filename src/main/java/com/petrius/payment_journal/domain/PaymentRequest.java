package com.petrius.payment_journal.domain;

import com.petrius.payment_journal.entity.PaymentType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PaymentRequest {

    @NotNull(message = "Balance is required")
    private BigDecimal amount;

    @NotNull(message = "payment type is mandatory")
    private PaymentType paymentType;
    private String description;
}
