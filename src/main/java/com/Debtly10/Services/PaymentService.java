package com.Debtly10.Services;

import com.Debtly10.DTOS.PaymentRegistrationDTO;
import com.Debtly10.Repository.MortgageRepository;
import com.Debtly10.Repository.PaymentRepository;
import com.Debtly10.models.Customer;
import com.Debtly10.models.Mortgage;
import com.Debtly10.models.Payment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
        mortgage.setLastPaid(payment.getDate());
        mortgageRepository.save(mortgage);
        payment.setMortgage(mortgage);
        paymentRepository.save(payment);
        return " payment added successfully";
    }

    public List<Payment> getAllPayment() {
        return paymentRepository.findAll();
    }

    public void deletePayment(Long pid) {
        paymentRepository.deleteById(pid);
    }


//    Mortgage mortgage= new Mortgage();
//        mortgage.setGivenAmount(mortgageRegistrationDTO.getGivenAmount());
//        mortgage.setLastPaid(mortgageRegistrationDTO.getLastPaid());
//        mortgage.setIssueDate(mortgageRegistrationDTO.getIssueDate());
//        mortgage.setLeftAmount(mortgageRegistrationDTO.getLeftAmount());
//        mortgage.setProductName(mortgageRegistrationDTO.getProductName());
//        mortgage.setMarketValue(mortgageRegistrationDTO.getMarketValue());
//    Customer customer = customerRepository.findById(id).get();
//        mortgage.setCustomer(customer);
//        mortgageRepository.save(mortgage);
//        return "mortgage added successfully";
}
