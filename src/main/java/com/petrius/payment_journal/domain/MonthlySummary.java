package com.petrius.payment_journal.domain;

import com.petrius.payment_journal.entity.Payment;
import com.petrius.payment_journal.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MonthlySummary {

    private YearMonth yearMonth;
    private BigDecimal totalAmount;
    private int paymentCount;
    private List<Payment> allPaymentsInMonth;

    private BigDecimal expectedAmount;
    private BigDecimal shouldPay;
    private BigDecimal dept;
    private BigDecimal overpay;
    private Status status;
}
