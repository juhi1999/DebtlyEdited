package com.Debtly10.Services;

import com.Debtly10.DTOS.PaymentRegistrationDTO;
import com.Debtly10.Repository.MortgageRepository;
import com.Debtly10.Repository.PaymentRepository;
import com.Debtly10.models.Mortgage;
import com.Debtly10.models.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private PaymentRepository paymentRepository;
    private MortgageRepository mortgageRepository;

    public PaymentService(PaymentRepository paymentRepository, MortgageRepository mortgageRepository) {
        this.paymentRepository = paymentRepository;
        this.mortgageRepository=mortgageRepository;
    }


    public String addPayment(PaymentRegistrationDTO paymentRegistrationDTO, Long mid) {

        Payment payment = new Payment();
        payment.setAmount(paymentRegistrationDTO.getAmount());
        payment.setDate(paymentRegistrationDTO.getDate());
        Mortgage mortgage = mortgageRepository.findById(mid).get();
        float temp= mortgage.getLeftAmount();
        float newAmount = temp - payment.getAmount();
        mortgage.setLeftAmount(newAmount);
        mortgageRepository.save(mortgage);
        payment.setMortgage(mortgage);
        return " payment added successfully";
    }
}
