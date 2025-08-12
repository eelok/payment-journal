package com.petrius.payment_journal.repository;

import com.petrius.payment_journal.entity.Payment;
import com.petrius.payment_journal.entity.PaymentType;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentRepository {

    private List<Payment> paymentList = new ArrayList<>();

    public PaymentRepository(ArrayList<Payment> paymentList) {
//        this.paymentList = paymentList;
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
        Payment payment6 = Payment.builder()
                .id(107L)
                .amount(new BigDecimal("38500"))
                .dateTime(LocalDateTime.of(2025, 7, 12, 0,0))
                .paymentType(PaymentType.REGULAR)
                .build();

        this.paymentList.add(payment1);
        this.paymentList.add(payment2);
        this.paymentList.add(payment3);
        this.paymentList.add(payment4);
        this.paymentList.add(payment5);
        this.paymentList.add(payment6);
    }

    public Payment add(Payment payment){
        Long id = generateId();
        payment.setId(id);
        this.paymentList.add(payment);
        return payment;
    }

    public List<Payment> getAll() {
        return new ArrayList<>(this.paymentList);
    }

    private Long generateId(){
        Long maxId =  this.paymentList
                .stream()
                .map(Payment::getId)
                .max(Long::compareTo)
                .orElse(0L);

        return maxId + 1;
    }
}
