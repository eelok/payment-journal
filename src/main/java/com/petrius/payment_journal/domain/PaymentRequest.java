package com.petrius.payment_journal.domain;

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

    private String description;
}
