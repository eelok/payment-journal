package com.petrius.payment_journal.service;


import com.petrius.payment_journal.domain.MonthlySummary;
import com.petrius.payment_journal.domain.PaymentRequest;
import com.petrius.payment_journal.domain.PaymentResponse;
import com.petrius.payment_journal.entity.Payment;
import com.petrius.payment_journal.mapper.PaymentMapper;
import com.petrius.payment_journal.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public PaymentService(PaymentRepository paymentRepository, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }

    public PaymentResponse add(PaymentRequest paymentRequest){
        Payment payment = this.paymentMapper.toPayment(paymentRequest);
        payment.setDateTime(LocalDateTime.now());
        Payment addedPayment = this.paymentRepository.add(payment);
        return this.paymentMapper.toPaymentResponse(addedPayment);
    }


    public List<PaymentResponse> getPaymentList() {
        List<Payment> paymentList = this.paymentRepository.getAll();
        List<PaymentResponse> paymentResponseList = paymentList.stream()
                .map(payment -> this.paymentMapper.toPaymentResponse(payment))
                .toList();

        return paymentResponseList;
    }

    public List<MonthlySummary> getMonthlyPaymentSummary(){
        List<Payment> paymentList = this.paymentRepository.getAll();
        Map<YearMonth, List<Payment>> paymentByMonth = new HashMap<>();


        paymentList.forEach(payment -> {
            LocalDateTime dateTime = payment.getDateTime();
            YearMonth yearMonth = YearMonth.from(dateTime);

            if(paymentByMonth.containsKey(yearMonth)){
                paymentByMonth.get(yearMonth).add(payment);
            } else {
                List<Payment> newPaymentList = new ArrayList<>();
                newPaymentList.add(payment);
                paymentByMonth.put(yearMonth, newPaymentList);
            }
        });

        List<MonthlySummary> monthlySummaryList = new ArrayList<>();
        for(Map.Entry<YearMonth, List<Payment>> entry: paymentByMonth.entrySet()){
            List<Payment> payments = entry.getValue();

            BigDecimal totalAmount = payments.stream()
                    .map(Payment::getAmount)
                    .reduce(BigDecimal.ZERO, (totalInMonth, currPayment) -> totalInMonth.add(currPayment));


            monthlySummaryList.add(new MonthlySummary(
                    entry.getKey(),
                    totalAmount,
                    payments.size(),
                    payments
            ));
        }

        monthlySummaryList.sort((s1, s2) -> s1.getYearMonth().compareTo(s2.getYearMonth()));
        return  monthlySummaryList;
    }
}
