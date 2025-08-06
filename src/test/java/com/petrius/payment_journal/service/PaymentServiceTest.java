package com.petrius.payment_journal.service;

import com.petrius.payment_journal.domain.MonthlySummary;
import com.petrius.payment_journal.entity.Payment;
import com.petrius.payment_journal.entity.PaymentType;
import com.petrius.payment_journal.mapper.PaymentMapper;
import com.petrius.payment_journal.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;
    @Mock
    private PaymentMapper paymentMapper;

    @InjectMocks
    PaymentService paymentService;


    @Test
    void shouldTest_getMonthlyPaymentSummary(){
        Payment payment1 = Payment.builder()
                .id(101L)
                .amount(new BigDecimal("2300"))
                .dateTime(LocalDateTime.of(2025, 8, 1, 0,0))
                .paymentType(PaymentType.REGULAR)
                .build();
        Payment payment2 = Payment.builder()
                .id(102L)
                .amount(new BigDecimal("5500"))
                .dateTime(LocalDateTime.of(2025, 8, 1, 0,0))
                .paymentType(PaymentType.REGULAR)
                .build();
        Payment payment3 = Payment.builder()
                .id(103L)
                .amount(new BigDecimal("6130"))
                .dateTime(LocalDateTime.of(2025, 8, 2, 0,0))
                .paymentType(PaymentType.REGULAR)
                .build();


        Payment payment4 = Payment.builder()
                .id(104L)
                .amount(new BigDecimal("12700"))
                .dateTime(LocalDateTime.of(2025, 7, 5, 0,0))
                .paymentType(PaymentType.REGULAR)
                .build();
        Payment payment5 = Payment.builder()
                .id(105L)
                .amount(new BigDecimal("10000"))
                .dateTime(LocalDateTime.of(2025, 7, 2, 0,0))
                .paymentType(PaymentType.REGULAR)
                .build();
        List<Payment> paymentList = List.of(payment1, payment2, payment3, payment4, payment5);

        when(this.paymentRepository.getAll()).thenReturn(paymentList);

        List<MonthlySummary> monthlyPaymentSummery = paymentService.getMonthlyPaymentSummary();



        MonthlySummary monthlySummaryJuli = new MonthlySummary(
                YearMonth.of(2025, 7),
                new BigDecimal("22700"),
                2,
                List.of(payment4, payment5)
        );
        MonthlySummary monthlySummaryAugust = new MonthlySummary(
                YearMonth.of(2025, 8),
                new BigDecimal("13930"),
                3,
                List.of(payment1, payment2, payment3)
        );

        List<MonthlySummary> expectedMonthlyPaymentSummary = List.of(monthlySummaryJuli, monthlySummaryAugust);

        assertEquals(expectedMonthlyPaymentSummary, monthlyPaymentSummery);
    }
}
