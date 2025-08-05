package com.petrius.payment_journal.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RentalPricing {

    private BigDecimal rentRate;
    private BigDecimal utilityFee;
    private LocalDate effectiveDate; //when rates become active;
    private LocalDateTime createdAt;
    private String note;
}
