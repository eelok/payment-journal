package com.petrius.payment_journal.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Payment {


    private BigDecimal amount;
    private LocalDate date;
    private String description;
    private PaymentType paymentType;


}
