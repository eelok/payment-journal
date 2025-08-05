package com.petrius.payment_journal.repository;

import com.petrius.payment_journal.entity.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentRepository {

    private ArrayList<Payment> paymentList = new ArrayList<>();


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
